package com.practice.ratingsdataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.ratingsdataservice.entity.Ratings;

@Repository
public interface IAddRatingRepository extends JpaRepository<Ratings, Long> {

}
