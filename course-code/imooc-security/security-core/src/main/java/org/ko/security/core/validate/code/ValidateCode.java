package org.ko.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ValidateCode implements Serializable{

    /**
     * 验证码生产构造函数
     * @param code 验证编码
     * @param expireTime 生成时间
     */
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 验证码验证构造函数
     * @param code 验证编码
     * @param expireIn 有效时间 5/s 单位(秒)
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }


    /**
     * 验证码code
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
