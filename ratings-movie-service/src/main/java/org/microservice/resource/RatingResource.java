package org.microservice.resource;

import java.util.Arrays;
import java.util.List;

import org.microservice.model.Rating;
import org.microservice.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId, 4);
	}
	
	@GetMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId)
	{
		UserRating userRating =new UserRating();
		userRating.initData(userId);
		return userRating;
	}
}
