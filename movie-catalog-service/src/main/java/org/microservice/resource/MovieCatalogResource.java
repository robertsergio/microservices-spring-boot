package org.microservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.microservice.model.CatalogItem;
import org.microservice.model.UserRating;
import org.microservice.services.MovieInfo;
import org.microservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingInfo userRatingInfo;

	@GetMapping("/{userid}")
	public List<CatalogItem> getCatalog(@PathVariable("userid") String userid) {

		UserRating userRating = userRatingInfo.getUserRating(userid);

		return userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

}
