package com.fanxl.security.app;

import com.fanxl.security.core.social.FanxlSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	/* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(StringUtils.equals(beanName, "fanxlSocialSecurityConfig")){
			FanxlSpringSocialConfigurer config = (FanxlSpringSocialConfigurer)bean;
			config.signupUrl("/social/signUp");
			return config;
		}
		return bean;
	}

}