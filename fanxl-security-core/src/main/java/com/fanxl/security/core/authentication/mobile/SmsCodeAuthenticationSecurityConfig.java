package com.fanxl.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/26 20:53
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler fanxlAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler fanxlAuthenctiationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter codeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        codeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        codeAuthenticationFilter.setAuthenticationSuccessHandler(fanxlAuthenticationSuccessHandler);
        codeAuthenticationFilter.setAuthenticationFailureHandler(fanxlAuthenctiationFailureHandler);

        SmsCodeAuthenticationProvider codeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        codeAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(codeAuthenticationProvider)
                .addFilterAfter(codeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
