package com.investmentsite.isc;

import com.investmentsite.isc.configurable.RestTemplateClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(RestTemplateClient.class)
@SpringBootApplication
public class IscApplication {

	public static void main(String[] args) {
		SpringApplication.run(IscApplication.class, args);
	}

}
