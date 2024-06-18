package org.ko.oauth.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 扫描controller包生成API接口文档
     */
    @Bean
    public Docket packageApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("app")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ko.auth"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OAuth2 鉴权服务器")
                .description("OAuth2鉴权服务器，包括认证服务器，资源服务器")
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0")
                .build();
    }

}
