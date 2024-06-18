package org.ko.security.core.properties;

public class SmsCodeProperties {

    /**
     * 随机产生字符数量
     */
    private int length = 6;

    /**
     * 失效时间
     */
    private int expireIn = 60;

    /**
     *
     */
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
