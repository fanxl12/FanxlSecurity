package com.fanxl.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/26 10:21
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{

    @Override
    public void send(String mobile, String code) {
        log.info("向手机:"+mobile+"发送验证码:"+code);
    }
}
