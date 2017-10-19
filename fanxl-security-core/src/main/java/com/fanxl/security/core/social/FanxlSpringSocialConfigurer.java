package com.fanxl.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 用来个性化授权地址的，默认是auth
 * @author zhailiang
 *
 */
public class FanxlSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;
	
	public FanxlSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

}