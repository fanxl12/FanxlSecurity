package com.fanxl.security.core;

import com.fanxl.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author: Fanxl
 * @Date: Created in 2017/9/20 16:11
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {


}
