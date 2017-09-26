package com.fanxl.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/20 14:10
 */
@Data
public class BrowserProperties {

    //这里需要加/
    private String loginPage = "/sign.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;
}
