package com.fanxl.security.core.properties;

/**
 * 验证码配置
 * @author zhailiang
 *
 */
public class ValidateCodeProperties {

    /**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();

	public ImageCodeProperties getImage() {
		return image; 
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

}