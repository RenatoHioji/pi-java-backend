package com.sunside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SunsideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunsideApplication.class, args);
	}

}
