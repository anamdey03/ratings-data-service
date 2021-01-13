package com.practice.ratingsdataservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ratingsdataservice.entity.Ratings;
import com.practice.ratingsdataservice.entity.UserRating;
import com.practice.ratingsdataservice.entity.UserRatings;
import com.practice.ratingsdataservice.service.IRatingsService;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsController {

	@Autowired
	private IRatingsService ratingService;

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		return ratingService.getMovieRatings(userId);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public UserRatings getRatingsForAllUsers() {
		return ratingService.getMovieRatingsForAllUsers();
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
	public ResponseEntity<String> addRating(@PathVariable(value = "userId") String userId,
			@RequestBody Ratings rating) {
		List<Ratings> ratings = ratingService.getMovieRatings(userId).getRatings();
		List<String> movieIds = new ArrayList<String>();
		for (Ratings rat : ratings) {
			movieIds.add(rat.getMovieId());
		}
		if (!movieIds.contains(rating.getMovieId())) {
			rating.setUserId(userId);
			ratingService.addRating(rating);
			return new ResponseEntity<String>("Movie Ratings Added Successfully for User " + userId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Movie Ratings already Present for the User " + userId,
					HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public void updateRating(@PathVariable(value = "userId") String userId, @RequestBody Ratings rating) {
		List<Ratings> ratings = ratingService.getMovieRatings(userId).getRatings();
		List<String> movieIds = new ArrayList<String>();
		for (Ratings rat : ratings) {
			movieIds.add(rat.getMovieId());
		}
		if (movieIds.contains(rating.getMovieId())) {
			ratingService.updateRatings(userId, rating.getRating(), rating.getMovieId());
		}
	}
	
	@RequestMapping(value = "/user/{userId}/{movieId}", method = RequestMethod.DELETE)
	public void deleteRating(@PathVariable(value = "userId") String userId, @PathVariable(value = "movieId") String movieId) {
		List<Ratings> ratings = ratingService.getMovieRatings(userId).getRatings();
		List<String> movieIds = new ArrayList<String>();
		for (Ratings rat : ratings) {
			movieIds.add(rat.getMovieId());
		}
		if (movieIds.contains(movieId)) {
			ratingService.deleteRatings(userId, movieId);
		}
	}
}
