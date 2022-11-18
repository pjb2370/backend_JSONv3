package com.investmentsite.isc;

import com.investmentsite.isc.configurable.RestTemplateClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Import(RestTemplateClient.class)
@SpringBootApplication
public class IscApplication {

	public static void main(String[] args) {
		SpringApplication.run(IscApplication.class, args);
	}

	// CORS 설정
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/api/**").allowedOrigins("*");
	}

}
