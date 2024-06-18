package com.imooc.controller;

import com.imooc.redis.RedisClient;
import com.imooc.support.Response;
import com.imooc.thrift.ServiceProvider;
import com.imooc.thrift.user.UserInfo;
import com.imooc.thrift.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * description: UserController <br>
 * date: 2020/2/29 19:38 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    @GetMapping("login")
    public String login() {
        return "login view";
    }

    @PostMapping("login")
    public Response login(@RequestParam String username,
                          @RequestParam String password) {
        //1.验证用户名密码
        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }

        if (userInfo == null) {
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if (!userInfo.getPassword().equalsIgnoreCase(md5(password))) {
            return Response.USERNAME_PASSWORD_INVALID;
        }

        //2.生成token
        String token = getToken();

        //3.缓存用户
        redisClient.set(token, toDTO(userInfo), 3600);

        return new Response<>(token);
    }

    @GetMapping("code")
    public Response<?> sendVerifyCode(@RequestParam(required = false) String mobile,
                                   @RequestParam(required = false) String email) throws TException {
        String message = "Verify code is: ";
        String code = randomCode("0123456789", 6);
        boolean result;
        //1.验证验证码
        if (StringUtils.isNotBlank(mobile)) {
            result = serviceProvider.getMessageService().sendMobileMessage(mobile, message + code);
            redisClient.set(mobile, code);
        } else if (StringUtils.isNotBlank(email)) {
            result = serviceProvider.getMessageService().sendEmailMessage(email, message + code);
            redisClient.set(email, code);
        } else {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if (!result) {
            return Response.SEND_VERIFY_CODE_FAILED;
        }

        return Response.SUCCESS;
    }

    @PostMapping("register")
    public Response<?> register(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam(required = false) String mobile,
                                      @RequestParam(required = false) String email,
                                      @RequestParam String verifyCode) throws TException {
        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        //1.验证验证码
        if (StringUtils.isNotBlank(mobile)) {
            String code = redisClient.get(mobile);
            if (!verifyCode.equals(code)) {
                return Response.VERIFY_CODE_INVALID;
            }

        } else {
            String code = redisClient.get(email);
            if (!verifyCode.equals(code)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);
        serviceProvider.getUserService().registerUser(userInfo);

        return Response.SUCCESS;
    }

    @PostMapping("authentication")
    public UserDTO authentication(@RequestHeader String token) {
        return redisClient.get(token);
    }

    private Object toDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);
        return userDTO;
    }

    private String getToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {
        StringBuilder builder = new StringBuilder(size);

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            builder.append(s.charAt(loc));
        }

        return builder.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(digest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
