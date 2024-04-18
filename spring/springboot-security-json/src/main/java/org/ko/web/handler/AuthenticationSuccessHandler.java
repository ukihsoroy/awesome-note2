package org.ko.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ko.web.base.ApiCode;
import org.ko.web.base.Render;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String ajaxHeader = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            String principal = authentication.getPrincipal().toString();
            Render<String> render = new Render<>(ApiCode.SESSION_TIMEOUT);
            render.setData(principal);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().print(mapper.writeValueAsString(render));
            response.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
