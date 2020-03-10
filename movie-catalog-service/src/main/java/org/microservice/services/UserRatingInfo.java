package org.microservice.services;

import java.util.Arrays;

import org.microservice.model.Rating;
import org.microservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate template;

	@HystrixCommand(fallbackMethod = "getUserRatingFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	public UserRating getUserRating(String userid) {
		return template.getForObject("http://ratings-movie-service/ratingdata/users/" + userid, UserRating.class);
	}

	public UserRating getUserRatingFallback(String userid) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userid);
		userRating.setRatings(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}
}
