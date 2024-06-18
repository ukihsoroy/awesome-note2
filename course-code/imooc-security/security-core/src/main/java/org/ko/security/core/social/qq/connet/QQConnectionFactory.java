package org.ko.security.core.social.qq.connet;

import org.ko.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        /**
         * providerId: 提供商唯一标识
         */
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
