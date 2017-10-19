package com.fanxl.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/19 19:04
 */
@RestController
@Slf4j
@RequestMapping("user")
public class UserRestController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request){

        //不管是注册用户还是绑定用户，都会拿到一个用户的唯一标识
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id){
        return "查询用户:"+id;
    }

    @GetMapping("/me")
    public Object getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

//    @GetMapping("/me")
//    public Object getMe(Authentication authentication) {
////        return SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
//    }

}
