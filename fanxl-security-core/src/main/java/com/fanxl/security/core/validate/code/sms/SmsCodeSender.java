package com.fanxl.security.core.validate.code.sms;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/26 10:20
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
