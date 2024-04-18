package org.ko.security.core.social.qq.conf;

import org.ko.security.core.properties.QQProperties;
import org.ko.security.core.properties.SecurityProperties;
import org.ko.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
//只有当ko.security.social.qq.app-id有值时才这个配置项才生效
@ConditionalOnProperty(prefix = "ko.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired private SecurityProperties properties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties config = properties.getSocial().getQq();
        return new QQConnectionFactory(config.getProviderId(), config.getAppId(), config.getAppSecret());
    }
}
