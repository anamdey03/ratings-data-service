package com.practice.ratingsdataservice.service;

import com.practice.ratingsdataservice.entity.Ratings;
import com.practice.ratingsdataservice.entity.UserRating;
import com.practice.ratingsdataservice.entity.UserRatings;

public interface IRatingsService {

	UserRating getMovieRatings(String userId);
	UserRatings getMovieRatingsForAllUsers();
	void addRating(Ratings rating);
	void updateRatings(String userId, int ratingId, String movieId);
	void deleteRatings(String userId, String movieId);
}