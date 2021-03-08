package com.nse.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class WalletAccountApplication {

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		
		return WebClient.builder();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(WalletAccountApplication.class, args);
	}

}
