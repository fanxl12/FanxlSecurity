package com.fanxl.security.core.social.qq.connet;

import com.fanxl.security.core.social.qq.api.QQ;
import com.fanxl.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @description ServiceProvider的实现，需要OAuth2Template和AbstractOAuth2ApiBinding
 * @author: Fanxl
 * @date: Created in 2017/9/28 9:33
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;


    /**
     * 认证流程的第一步地址
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 认证流程的第四步地址
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        // 传入OAuth2Template的实现
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId=appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        // 每个人用户信息都不一样，所以每次都是new一遍
        return new QQImpl(accessToken, appId);
    }
}
