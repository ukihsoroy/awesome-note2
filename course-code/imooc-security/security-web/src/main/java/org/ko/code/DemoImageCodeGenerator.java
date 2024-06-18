package org.ko.code;

import org.ko.security.core.validate.code.IValidateCodeGenerator;
import org.ko.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements IValidateCodeGenerator{

    @Override
    public ImageCode generatorCode(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
