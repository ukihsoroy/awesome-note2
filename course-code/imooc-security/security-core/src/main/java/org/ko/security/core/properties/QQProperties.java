package org.ko.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class QQProperties extends SocialProperties {

    private static String providerId = "qq";

    public static String getProviderId() {
        return providerId;
    }

    public static void setProviderId(String providerId) {
        QQProperties.providerId = providerId;
    }
}
