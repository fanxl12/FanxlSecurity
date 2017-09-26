package com.fanxl.code;

import com.fanxl.security.core.validate.code.image.ImageCode;
import com.fanxl.security.core.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description
 * User: fanxl
 * Date: 2017/9/24
 * Time: 23:11
 */
@Slf4j
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

    @Override
    public ImageCode generate(ServletWebRequest request) {
        log.info("自己做验证码");
        return null;
    }
}
