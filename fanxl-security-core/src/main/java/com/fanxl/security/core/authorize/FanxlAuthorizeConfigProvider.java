package com.fanxl.security.core.authorize;

import com.fanxl.security.core.properties.SecurityConstants;
import com.fanxl.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class FanxlAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
    private SecurityProperties securityProperties;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
				securityProperties.getBrowser().getLoginPage(),
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
				securityProperties.getBrowser().getSignUpUrl(),
				securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
				securityProperties.getBrowser().getSignOutUrl())
		.permitAll();
	}

}