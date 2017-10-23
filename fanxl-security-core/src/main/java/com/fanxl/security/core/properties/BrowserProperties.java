package com.fanxl.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/20 14:10
 */
@Data
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    private String signUpUrl = "/signUp.html";

    //这里需要加/
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;
}
