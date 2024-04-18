package org.ko.security.core.validate.code;


import org.ko.security.core.properties.SecurityProperties;
import org.ko.security.core.validate.code.image.ImageCodeGenerator;
import org.ko.security.core.validate.code.sms.DefaultSmsCodeSender;
import org.ko.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired private SecurityProperties securityProperties;


    /**
     * 如果Spring容器中有 这个imageCodeGenerator名字的bean就不在初始化下面的了
     */
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    @Bean
    public IValidateCodeGenerator imageCodeGenerator () {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 如果Spring容器中有 这个imageCodeGenerator名字的bean就不在初始化下面的了
     */
    @ConditionalOnMissingBean(SmsCodeSender.class)
    @Bean
    public SmsCodeSender smsCodeSender () {
        return new DefaultSmsCodeSender();
    }

}
