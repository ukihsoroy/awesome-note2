package org.ko.security.core.validate.code.image;

import org.ko.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode extends ValidateCode {

    /**
     * 验证码生产构造函数
     * @param image 验证图片
     * @param code 验证编码
     * @param expireTime 生成时间
     */
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    /**
     * 验证码验证构造函数
     * @param image 验证图片
     * @param code 验证编码
     * @param expireIn 有效时间 5/s 单位(秒)
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }


    /**
     * 图片验证码
     */
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
