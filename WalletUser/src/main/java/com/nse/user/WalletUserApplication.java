package com.nse.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WalletUserApplication {
	
	
	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(WalletUserApplication.class, args);
	}

}
