package com.fanxl.security.core.social;

import javax.sql.DataSource;

import com.fanxl.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
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
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
	private DataSource dataSource;

    @Autowired
	private SecurityProperties securityProperties;

    @Autowired(required = false)
	private ConnectionSignUp connectionSignUp;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//		repository.setTablePrefix("imooc_");
		if (connectionSignUp!=null){
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}
	
	@Bean
	public SpringSocialConfigurer fanxlSocialSecurityConfig() {
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		FanxlSpringSocialConfigurer configurer = new FanxlSpringSocialConfigurer(filterProcessesUrl);
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		return configurer;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator)) {
		};
	}
	
}