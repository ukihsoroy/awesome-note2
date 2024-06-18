package org.ko.spring.https;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

public class HttpsTemplate {

    private RestTemplate restTemplate;

    public HttpsTemplate () {
//        System.setProperty("javax.net.debug", "all");
        restTemplate = new RestTemplate(new HttpsClientRequestFactory());
    }

    /**
     * get for response entity
     * @param url 请求url
     * @param responseType 返回值类型
     */
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, new HttpHeaders());
        URI uri = URI.create(url);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType);
    }

    /**
     * get for object 根据设定类型直接返回对象
     * @param url 请求url
     * @param responseType 返回值类型
     */
    public <T> T getForObject(String url, Class<T> responseType) {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, new HttpHeaders());
        URI uri = URI.create(url);
        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return null;
    }

    /**
     * 自动处理参数拼接，url不能带有拼接的参数
     * @param url 基础url, 不带参数
     * @param params 参数k v
     * @param responseType 返回值类型
     */
    public <T> T getForObject(String url, Map<String, String> params, Class<T> responseType) {
        StringBuilder urlBuffer = new StringBuilder(url);
        if (!params.isEmpty()) {
            urlBuffer.append("?");
            params.forEach((k, v) -> urlBuffer.append(k).append("=").append(v).append("&"));
        }
        return getForObject(urlBuffer.toString(), responseType);
    }

    /**
     * post 获取返回entity
     * @param url 请求基础url
     * @param params 请求拼接参数
     * @param body 请求body
     * @param headers 请求header
     * @param responseType 返回值类型
     */
    public <T> ResponseEntity<T> postForEntity(String url,
                               Map<String, String> params,
                               Object body,
                               HttpHeaders headers,
                               Class<T> responseType) {
        StringBuilder urlBuffer = new StringBuilder(url);
        if (!params.isEmpty()) {
            urlBuffer.append("?");
            params.forEach((k, v) -> urlBuffer.append(k).append("=").append(v).append("&"));
        }
        return postForEntity(urlBuffer.toString(), body, headers, responseType);
    }

    /**
     *
     * @param url 拼接后的url
     * @param body 请求方法体
     * @param headers 请求header
     * @param responseType 返回值类型
     */
    public <T> ResponseEntity<T> postForEntity(String url,
                                               Object body,
                                               HttpHeaders headers,
                                               Class<T> responseType) {
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, headers);
        URI uri = URI.create(url);
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, responseType);
    }

    /**
     * post url 直接返回对象
     * @param url 请求url 已经拼接好参数
     * @param body 请求body
     * @param headers 请求header
     * @param responseType 返回值类型
     */
    public <T> T postForObject(String url,
                               Object body,
                               HttpHeaders headers,
                               Class<T> responseType) {
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, headers);
        URI uri = URI.create(url);
        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, responseType);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }

        return null;
    }

    /**
     * post 获取返回 object
     * @param url 请求基础url
     * @param params 请求拼接参数
     * @param body 请求body
     * @param headers 请求header
     * @param responseType 返回值类型
     */
    public <T> T postForObject(String url,
                               Map<String, String> params,
                               Object body,
                               HttpHeaders headers,
                               Class<T> responseType) {
        StringBuilder urlBuffer = new StringBuilder(url);
        if (!params.isEmpty()) {
            urlBuffer.append("?");
            params.forEach((k, v) -> urlBuffer.append(k).append("=").append(v).append("&"));
        }
        return postForObject(urlBuffer.toString(), body, headers, responseType);
    }
}
