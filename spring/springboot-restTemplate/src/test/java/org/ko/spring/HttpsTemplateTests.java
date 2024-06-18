package org.ko.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ko.spring.https.HttpsClientRequestFactory;
import org.ko.spring.https.HttpsTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpsTemplateTests {

    private HttpsTemplate httpsTemplate;

    String url = "https://vdm-qa.apps.pp01.cnnorth.cf.ford.com.cn/fordvdm/vehicle/comparator/models?marketName=l_cn_zh&nameplateModelKeyMap=%7B%22WSPAD-CD9-2018-LincolnMKXCHN%22%3A%5B%22YZDCD%22%2C%22YZBBA%22%2C%22YZBB8%22%2C%22YZBCA%22%5D%2C%22WSPAD-CD9-2019-LINCOLNMKXCHN%22%3A%5B%22YZBAB%22%2C%22YZBBA%22%2C%22YZBCA%22%2C%22YZBB8%22%5D%7D";

    @BeforeEach
    public void setup () {
        System.setProperty("javax.net.debug", "all");
        httpsTemplate = new HttpsTemplate();
    }

    @Test
    public void whenGetUrlSuccess () {
        String response = httpsTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

    @Test
    public void whenGetUrlWithParamsSuccess () {
        String u = "https://vdm-qa.apps.pp01.cnnorth.cf.ford.com.cn/fordvdm/vehicle/comparator/models";
        Map<String, String> params = new HashMap<>();
        params.put("marketName", "l_cn_zh");
        params.put("nameplateModelKeyMap", "%7B%22WSPAD-CD9-2018-LincolnMKXCHN%22%3A%5B%22YZDCD%22%2C%22YZBBA%22%2C%22YZBB8%22%2C%22YZBCA%22%5D%2C%22WSPAD-CD9-2019-LINCOLNMKXCHN%22%3A%5B%22YZBAB%22%2C%22YZBBA%22%2C%22YZBCA%22%2C%22YZBB8%22%5D%7D");
        String response = httpsTemplate.getForObject(u, params, String.class);
        System.out.println(response);
    }

}
