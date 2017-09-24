package com.fanxl.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * Description
 * User: fanxl
 * Date: 2017/9/24
 * Time: 21:26
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
