package org.ko.security.core.social.qq.connet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class QQOAuth2Template extends OAuth2Template {

    private static final Logger _LOGGER = LoggerFactory.getLogger(QQOAuth2Template.class);

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {

        RestTemplate restTemplate =  super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        _LOGGER.info("获取Token access 的响应: {}", responseStr);
        String[] item = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
        String accessToken = StringUtils.substringAfterLast(item[0], "=");
        String expiresIn = StringUtils.substringAfterLast(item[1], "=");
        String refreshToken = StringUtils.substringAfterLast(item[2], "=");
        return new AccessGrant(accessToken, null, refreshToken, Long.parseLong(expiresIn));
    }
}
