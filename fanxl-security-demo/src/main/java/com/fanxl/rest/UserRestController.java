package com.fanxl.rest;

import com.fanxl.po.User;
import com.fanxl.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

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

    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request){

        //不管是注册用户还是绑定用户，都会拿到一个用户的唯一标识
//        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp("111", new ServletWebRequest(request));
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id){
        return "查询用户:"+id;
    }

    @GetMapping("/me")
    public Object getMe(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {

        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");

        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");
        log.info(company);

        return user;
    }

//    @GetMapping("/me")
//    public Object getMe(Authentication authentication) {
////        return SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
//    }

}
