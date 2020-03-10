package org.microservice.ratingsmovieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"org.microservice.resource"})
@EnableEurekaClient
public class RatingsMovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsMovieServiceApplication.class, args);
	}

}
