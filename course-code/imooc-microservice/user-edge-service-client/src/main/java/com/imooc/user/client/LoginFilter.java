package com.imooc.user.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imooc.thrift.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * description: LoginFilter <br>
 * date: 2020/3/1 15:44 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public abstract class LoginFilter implements Filter {

    private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder().maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES).build();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getParameter("token");

        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        UserDTO userDTO = null;

        if (StringUtils.isNotBlank(token)) {
            //优先取客户端缓存
            userDTO = cache.getIfPresent(token);
            if (userDTO == null) {
                userDTO = requestUserInfo(token);
                if (userDTO != null) {
                    //加入缓存
                    cache.put(token, userDTO);
                }
            }
        }

        if (userDTO == null) {
            response.sendRedirect("http://www.sigma.com/user/login");
        }

        login(request, response, userDTO);

        filterChain.doFilter(request, response);
    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);


    private UserDTO requestUserInfo(String token) {
        //还不是域名
        String url = "http://www.sigma.com/user/authentication";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("token", token);
        InputStream inputStream = null;
        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! StatusLine: " + response.getStatusLine().getStatusCode());
            }
            inputStream = response.getEntity().getContent();
            byte[] bytes = new byte[1024];
            StringBuilder builder = new StringBuilder();
            int len = 0;
            while ((len = inputStream.read(bytes)) > 0) {
                builder.append(new String(bytes, 0, len));
            }

            return new ObjectMapper().readValue(builder.toString(), UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}
