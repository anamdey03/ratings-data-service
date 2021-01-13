package com.practice.ratingsdataservice.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.practice.ratingsdataservice.entity.Ratings;

@Repository
public class RatingRepository extends AbstractDao implements IRatingRepository {

	@Override
	public List<Ratings> getRatingsById(String userId) {
		TypedQuery<Ratings> query = getEm().createNamedQuery("Ratings.getRatingByUserId", Ratings.class);
		query.setParameter("uId", userId);
		List<Ratings> ratings = query.getResultList();
		return ratings;
	}

	@Override
	public List<String> getRatingsAllUsers() {
		TypedQuery<String> query = getEm().createNamedQuery("Ratings.getUserIdsRatings", String.class);
		List<String> userIds = query.getResultList();
		return userIds;
	}
	
	@Transactional
	@Override
	public void updateRatings(String userId, int ratingId, String movieId) {
		Query query = getEm().createNamedQuery("Ratings.updateUserRating");
		query.setParameter("uId", userId);
		query.setParameter("uRating", ratingId);
		query.setParameter("uMovieId", movieId);
		query.executeUpdate();
	}
	
	@Transactional
	@Override
	public void deleteRatings(String userId, String movieId) {
		Query query = getEm().createNamedQuery("Ratings.deleteUserRating");
		query.setParameter("uId", userId);
		query.setParameter("uMovieId", movieId);
		query.executeUpdate();
	}
}
