package org.ko.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface IValidateCodeGenerator {

    ValidateCode generatorCode(ServletWebRequest request);
}
