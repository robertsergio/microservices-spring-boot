package org.microservice.resource;

import org.microservice.model.Movie;
import org.microservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apikey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{movieID}")
	public Movie getMovieInfo(@PathVariable("movieID") String movieID)
	{
		System.out.println("calling getMovieInfo()...");
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/"+movieID+"?api_key="+apikey, 
				MovieSummary.class
		); 
		return new Movie(movieID, movieSummary.getTitle(),movieSummary.getOverview());
	}
	@GetMapping("/")
	public String test() {
		return "ok";
	}
}
