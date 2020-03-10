package org.microservice.moviecatalogservice;

import org.microservice.services.MovieInfo;
import org.microservice.services.UserRatingInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "org.microservice.resource" })
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableSwagger2
public class MovieCatalogServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getTemplate() {
		// HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
		// HttpComponentsClientHttpRequestFactory();
		// clientHttpRequestFactory.setConnectTimeout(3000);
		return new RestTemplate();
	}

	@Bean
	public MovieInfo getMovieInfo() {
		return new MovieInfo();
	}

	@Bean
	public UserRatingInfo getUserRatingInfo() {
		return new UserRatingInfo();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
