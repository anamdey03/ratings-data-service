package com.practice.ratingsdataservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.ratingsdataservice.entity.Ratings;
import com.practice.ratingsdataservice.entity.UserRating;
import com.practice.ratingsdataservice.entity.UserRatings;
import com.practice.ratingsdataservice.repository.IAddRatingRepository;
import com.practice.ratingsdataservice.repository.IRatingRepository;

@Service
public class RatingService implements IRatingsService {

	@Autowired
	private IRatingRepository ratingRepository;

	@Autowired
	private IAddRatingRepository addRatingRepository;

	@Override
	public UserRating getMovieRatings(String userId) {
		List<Ratings> ratings = ratingRepository.getRatingsById(userId);
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatings(ratings);
		return userRating;
	}

	@Override
	public UserRatings getMovieRatingsForAllUsers() {
		List<String> userIds = ratingRepository.getRatingsAllUsers();
		List<UserRating> userRatings = new ArrayList<UserRating>();
		UserRatings userRats = new UserRatings();
		for (String userId : userIds) {
			List<Ratings> ratings = new ArrayList<Ratings>();
			UserRating userRating = new UserRating();
			ratings = ratingRepository.getRatingsById(userId);
			userRating.setRatings(ratings);
			userRating.setUserId(userId);
			userRatings.add(userRating);
		}
		userRats.setUserRating(userRatings);
		return userRats;
	}

	@Override
	public void addRating(Ratings rating) {
		addRatingRepository.save(rating);
	}

	@Override
	public void updateRatings(String userId, int ratingId, String movieId) {
		ratingRepository.updateRatings(userId, ratingId, movieId);
	}
	
	@Override
	public void deleteRatings(String userId, String movieId) {
		ratingRepository.deleteRatings(userId, movieId);
	}

}
