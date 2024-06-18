package io.ukihsoroy;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

/**
 * @author K.O
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class GatlingAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatlingAutomationApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
