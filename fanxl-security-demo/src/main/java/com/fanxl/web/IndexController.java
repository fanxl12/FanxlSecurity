package com.fanxl.web;

import com.fanxl.security.browser.support.SocialUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * @author: fanxl
 * @date: 2017/10/21 22:10
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("")
    public String signUp(Model model, HttpServletRequest request){
        log.info(request.getContentType());
        return "index";
    }
}
