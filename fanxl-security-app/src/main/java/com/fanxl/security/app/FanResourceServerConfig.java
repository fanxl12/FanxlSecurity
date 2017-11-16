package com.fanxl.security.app;

import com.fanxl.security.app.authentication.openId.OpenIdAuthenticationSecurityConfig;
import com.fanxl.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.fanxl.security.core.authorize.AuthorizeConfigManager;
import com.fanxl.security.core.properties.SecurityConstants;
import com.fanxl.security.core.properties.SecurityProperties;
import com.fanxl.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableResourceServer
public class FanResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler fanxlAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler fanxlAuthenctiationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(fanxlAuthenticationSuccessHandler)
                .failureHandler(fanxlAuthenctiationFailureHandler);


        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(imoocSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());

    }
}