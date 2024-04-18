package org.ko.dashboard.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final String URL = "http://localhost:1337/rest/swagger-ui.html";
    private static final String EMAIL = "ko.shen@hotmail.com";


    /**
     * 通过 createRestApi函数来构建一个DocketBean
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                .select()
                /**
                 * 控制暴露出去的路径下的实例
                 * 如果某个接口不想暴露,可以使用以下注解
                 * @ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                 */
                .apis(RequestHandlerSelectors.basePackage("org.ko"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建API文档详细参数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Day video topN stat.")
                .contact(new Contact("K.O", URL, EMAIL))    //作者
                .version("1.0")
                .description("数据统计接口测试文档")
                .build();
    }
}
