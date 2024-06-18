package org.ko.security.core.social.qq.connet;

import org.ko.security.core.social.qq.api.QQ;
import org.ko.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试API是否可用
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * 设置ConnectionValues需要的数据
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        try {
            QQUserInfo userInfo = api.getUserInfo();
            //默认用户名
            values.setDisplayName(userInfo.getNickname());
            //用户头像
            values.setImageUrl(userInfo.getFigureurl_1());
            //用户主页
            values.setProfileUrl(null);
            //用户openid
            values.setProviderUserId(userInfo.getOpenid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param qq
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     * 更新
     * @param qq
     * @param message
     */
    @Override
    public void updateStatus(QQ qq, String message) {

    }
}
