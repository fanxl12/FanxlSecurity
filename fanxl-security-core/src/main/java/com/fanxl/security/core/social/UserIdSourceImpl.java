package com.fanxl.security.core.social;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.UserIdSource;

/**
 * @description
 * @author: fanxl
 * @date: 2017/11/13 17:47
 */
@Slf4j
public class UserIdSourceImpl implements UserIdSource {

    @Override
    public String getUserId() {
        log.info("获取UserId");
        return "111";
    }
}
