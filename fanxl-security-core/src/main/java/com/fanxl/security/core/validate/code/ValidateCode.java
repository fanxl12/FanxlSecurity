package com.fanxl.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/22 9:58
 */
@Data
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireInt) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
