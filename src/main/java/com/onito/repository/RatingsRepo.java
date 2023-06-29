package com.onito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onito.model.Ratings;

public interface RatingsRepo extends JpaRepository<Ratings, String>{

}
