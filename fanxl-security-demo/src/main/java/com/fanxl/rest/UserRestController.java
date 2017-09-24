package com.fanxl.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/19 19:04
 */
@RestController
@Slf4j
@RequestMapping("user")
public class UserRestController {

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
