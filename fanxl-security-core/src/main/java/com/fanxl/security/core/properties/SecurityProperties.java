package com.fanxl.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/20 14:09
 */
@Data
@ConfigurationProperties(prefix = "fanxl.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}
