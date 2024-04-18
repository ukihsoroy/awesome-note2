package org.ko.security.browser.authentication;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.ko.security.core.support.SimpleResponse;
import org.ko.security.core.properties.LoginType;
import org.ko.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登陆失败的回调
 * AuthenticationFailureHandler 失败处理器接口
 * SimpleUrlAuthenticationFailureHandler spring 默认的失败处理
 *
 */
@Component
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger _LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        _LOGGER.info("登陆失败;");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }


    }
}
