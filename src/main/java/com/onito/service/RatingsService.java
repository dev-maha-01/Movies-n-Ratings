package com.onito.service;

import java.util.List;

import com.onito.exception.RatingException;
import com.onito.model.Ratings;

public interface RatingsService {
	public String registerRatings(Ratings rat) throws RatingException;
	public String registerListOfRatings(List<Ratings>ratList) throws RatingException;
}
