package com.fanxl.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * @description 这个是为了试下授权用户系统不存在，自动给他注册一个用户
 * @author: Fanxl
 * @date: Created in 2017/10/19 18:06
 */
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {

        // 根据社交用户信息默认创建用户并返回用户唯一标识

        return connection.getDisplayName();
    }
}
