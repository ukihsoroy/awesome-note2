package org.ko.security.core.properties;

public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    /**
     * 图片宽
     */
    private int width = 80;

    /**
     * 图片高
     */
    private int height = 26;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
