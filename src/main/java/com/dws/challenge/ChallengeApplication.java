package com.dws.challenge;


import com.dws.challenge.service.AccountService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@OpenAPIDefinition
public class ChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);

	}

}
