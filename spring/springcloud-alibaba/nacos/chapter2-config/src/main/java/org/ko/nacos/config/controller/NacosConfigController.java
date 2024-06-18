package org.ko.nacos.config.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;


@RestController
@RefreshScope
@RequestMapping("user")
@Api(tags = "配置测试接口")
public class NacosConfigController {

    @Autowired private RestTemplate restTemplate;

    @Value("${user.name}")
    private String name;

    @Value("${user.age:25}")
    private int age;

    @GetMapping
    @ApiOperation("查看配置是否更新")
    public String info() {
        return "Hello Nacos Config!" + "Hello " + name + " " + age + "!";
    }

    /**
     * nacos API 使用restTemplate会出现问题，暂时还未找好更好的解决办法
     * @param name
     * @param age
     * @return
     * @throws NacosException
     */
    @PostMapping
    @ApiOperation("向Nacos注册中心添加配置信息，nacos API 使用restTemplate会出现问题，暂时还未找好更好的解决办法")
    public String postProperties(@RequestParam String name, @RequestParam String age) throws NacosException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dataId", "nacos-config-example.properties");
        params.add("group", "DEFAULT_GROUP");
        params.add("content", "user.id=1%0Auser.name=james%0Auser.age=17");
        String url = "http://127.0.0.1:8848/nacos/v1/cs/configs";

        String serverAddr = "localhost";
        String dataId = "nacos-config-example.properties";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);


        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

       return restTemplate.postForObject(url, params, String.class);
    }


}
