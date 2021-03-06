package com.fanxl.security.core.social;


import com.fanxl.security.core.properties.SecurityProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableSocial
@Order(10)
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
	private DataSource dataSource;

    @Autowired
	private SecurityProperties securityProperties;

    @Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	@Autowired(required = false)
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;


	/**
	 * 配置JdbcUsersConnectionRepository
	 * @param connectionFactoryLocator
	 * @return
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		// 第三个参数是加密的，开发暂时不加密
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		// 下面是配置表前缀
		repository.setTablePrefix("fan_");
		// 下面这个代码是为了QQ授权的时候，如果不存在自动注册用户
		if (connectionSignUp!=null){
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}

	/**
	 * 作用是在认证拦截器上添加social的认证拦截功能，引导用户完成认证过程
	 * @return
	 */
	@Bean
	public SpringSocialConfigurer fanxlSocialSecurityConfig() {
		// 个性化认证地址，默认是auth
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		FanxlSpringSocialConfigurer configurer = new FanxlSpringSocialConfigurer(filterProcessesUrl);
		// 告诉如果找不到用户跳转到指定的url上
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		return configurer;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
		return  providerSignInUtils;
	}


	@Override
	public UserIdSource getUserIdSource() {
		return new SecurityContextUserIdSource();
	}




}