package org.microservice.services;

import org.microservice.model.CatalogItem;
import org.microservice.model.Movie;
import org.microservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate template;

	@HystrixCommand(fallbackMethod = "getCatalogItemFallback")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	public CatalogItem getCatalogItemFallback(Rating rating) {
		return new CatalogItem("Not found movie", "", rating.getRating());
	}
}
