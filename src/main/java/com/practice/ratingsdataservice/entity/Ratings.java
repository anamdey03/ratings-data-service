package com.practice.ratingsdataservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({ @NamedQuery(name = "Ratings.getRatingByUserId", query = "from Ratings r where r.userId =:uId"),
	@NamedQuery(name = "Ratings.getUserIdsRatings", query = "Select distinct userId from Ratings"),
	@NamedQuery(name = "Ratings.updateUserRating", query = "Update Ratings r set r.rating =:uRating where r.userId =:uId and r.movieId =:uMovieId"),
	@NamedQuery(name = "Ratings.deleteUserRating", query = "Delete from Ratings r where r.userId =:uId and r.movieId =:uMovieId")})
public class Ratings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String movieId;
	private int rating;
	@JsonIgnore
	private String userId;

	public Ratings() {
		
	}

	public Ratings(String movieId, int rating, String userId) {
		super();
		this.movieId = movieId;
		this.rating = rating;
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
