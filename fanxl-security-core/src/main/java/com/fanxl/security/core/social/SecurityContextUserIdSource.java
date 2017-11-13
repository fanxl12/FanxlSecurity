package com.fanxl.security.core.social;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialUserDetails;

/**
 * @description
 * @author: fanxl
 * @date: 2017/11/13 17:32
 */
public class SecurityContextUserIdSource implements UserIdSource {

    @Override
    public String getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication!=null){
            if (authentication.getPrincipal() instanceof SocialUserDetails) {
                return ((SocialUserDetails)authentication.getPrincipal()).getUserId();
            }
            return null;
        }
        return null;
    }
}
