package com.fanxl.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description
 * User: fanxl
 * Date: 2017/9/24
 * Time: 23:00
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
