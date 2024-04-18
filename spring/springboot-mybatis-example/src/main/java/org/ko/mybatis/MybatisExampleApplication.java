package org.ko.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//springboot 启动注解
@SpringBootApplication
//mybatis mapper装载
@MapperScan(basePackages = "org.ko.mybatis.mapper")
//swagger 启动注解
@EnableSwagger2
public class MybatisExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisExampleApplication.class, args);
	}


	/**
	 * swagger 配置
	 * @return
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.ko"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("MyBatis-Example")
				.description("MyBatisExample测试代码")
				.version("1.0.0")
				.build();
	}

}
