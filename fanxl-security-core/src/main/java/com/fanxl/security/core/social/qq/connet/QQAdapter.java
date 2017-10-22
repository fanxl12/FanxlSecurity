package com.fanxl.security.core.social.qq.connet;

import com.fanxl.security.core.social.qq.api.QQ;
import com.fanxl.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;


/**
 * 把从QQ获取到的数据转成标准的认证数据
 * @author zhailiang
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 测试当前API是否可用
	 * @param api
	 * @return
	 */
	@Override
    public boolean test(QQ api) {
		return true;
	}

	/**
	 * 数据适配
	 * @param api
	 * @param values
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//do noting 这个是更新社交主页状态的
	}

}