package org.ko.spring;

import org.ko.spring.https.HttpsTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}

	/**
	 * 初始化RestTemplate
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HttpsTemplate httpsTemplate() {
		return new HttpsTemplate();
	}

}
