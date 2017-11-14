package com.fanxl.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * @author: fanxl
 * @date: 2017/10/21 22:10
 */
@Slf4j
@Controller
@RequestMapping("/demo-signUp")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("")
    public String signUp(Model model, HttpServletRequest request){

//        SocialUserInfo userInfo = new SocialUserInfo();
//        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
//        userInfo.setProviderId(connection.getKey().getProviderId());
//        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
//        userInfo.setNickname(connection.getDisplayName());
//        userInfo.setHeadimg(connection.getImageUrl());
//        log.info(userInfo.toString());

        return "demo-signUp";
    }
}
