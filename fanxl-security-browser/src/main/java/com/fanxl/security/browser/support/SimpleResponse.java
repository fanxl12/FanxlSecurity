package com.fanxl.security.browser.support;

import lombok.Data;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/20 14:02
 */
@Data
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

}
