package com.fanxl.security.browser;

import com.fanxl.security.core.authentication.AbstractChannelSecurityConfig;
import com.fanxl.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.fanxl.security.core.authorize.AuthorizeConfigManager;
import com.fanxl.security.core.properties.SecurityConstants;
import com.fanxl.security.core.properties.SecurityProperties;
import com.fanxl.security.core.validate.code.ValidateCodeSecurityConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @description
 * @author: Fanxl
 * @date: Created in 2017/9/20 13:12
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer fanxlSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(fanxlAuthenctiationFailureHandler);
//        validateCodeFilter.setSecurityProperties(securityProperties);
//        validateCodeFilter.afterPropertiesSet();
//
//        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//        smsCodeFilter.setAuthenticationFailureHandler(fanxlAuthenctiationFailureHandler);
//        smsCodeFilter.setSecurityProperties(securityProperties);
//        smsCodeFilter.afterPropertiesSet();

        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and() // 这里短信验证码和图形验证码的两个Filter合并为一个validateCodeSecurityConfig
//                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(fanxlSocialSecurityConfig)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin()) // 阻止第二个用户登录
                    .expiredSessionStrategy(sessionInformationExpiredStrategy) // 第二个用户把第一个用户踢下去了，第一个用户刷新提示，实现这个接口
//                    .invalidSessionUrl("/session/invalid")
                    .and()
                    .and()
                .logout()
//                    .logoutUrl("/signOut") // 指定退出的地址
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .deleteCookies("JSESSIONID")
                    .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }

    /**
     * 配置记住我的数据库连接
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
