package com.practice.ratingsdataservice.repository;

import java.util.List;

import com.practice.ratingsdataservice.entity.Ratings;

public interface IRatingRepository {

	public List<Ratings> getRatingsById(String userId);
	public List<String> getRatingsAllUsers();
	public void updateRatings(String userId, int ratingId, String movieId);
	public void deleteRatings(String userId, String movieId);
}
