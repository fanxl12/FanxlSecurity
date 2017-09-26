package com.fanxl.security.core.properties;

/**
 * 图片验证码配置项
 * 
 * @author zhailiang
 *
 */
public class SmsCodeProperties {

	private int length = 6;

	private int expireIn = 60;

	private String url;
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}