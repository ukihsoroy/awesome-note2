package com.imooc;

import org.apache.tomcat.util.buf.HexUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * description: PowerTests <br>
 * date: 2020/3/1 14:54 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class PowerTests {

    @Test public void test1() {
        System.out.println(md5("123456"));
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
