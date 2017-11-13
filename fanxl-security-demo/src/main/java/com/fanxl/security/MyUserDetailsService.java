package com.fanxl.security;

import com.fanxl.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/20 13:18
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("表单登录用户:"+username);
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("社交登录用户:"+userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId){
        //根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123456");
        log.info("password:"+password);

        User user = new User();
        user.setUserId("111");
        user.setUsername("123");
        user.setPassword(password);

        return user;

//        if (userId.equals("123")){
//            return user;
//        }else if (userId.equals("111")){
//            return user;
//        }else {
//            return new SocialUser(userId, password,
//                    true, true, true, true,
//                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        }
    }

}
