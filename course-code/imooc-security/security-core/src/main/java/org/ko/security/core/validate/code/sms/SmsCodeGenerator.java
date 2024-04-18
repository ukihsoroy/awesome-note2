package org.ko.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.ko.security.core.properties.SecurityProperties;
import org.ko.security.core.validate.code.IValidateCodeGenerator;
import org.ko.security.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@Component
public class SmsCodeGenerator implements IValidateCodeGenerator {


    @Autowired private SecurityProperties securityProperties;

    @Override
    public ValidateCode generatorCode(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
