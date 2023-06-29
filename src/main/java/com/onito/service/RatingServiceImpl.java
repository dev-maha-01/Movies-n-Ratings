package com.onito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onito.exception.RatingException;
import com.onito.model.Ratings;
import com.onito.repository.RatingsRepo;

@Service
public class RatingServiceImpl implements RatingsService{
	@Autowired
	private RatingsRepo rRepo;
	
	@Override
	public String registerRatings(Ratings rat) throws RatingException {
		rRepo.save(rat);
		return "success";
	}

	@Override
	public String registerListOfRatings(List<Ratings> ratList) throws RatingException {
		rRepo.saveAll(ratList);
		return "All ratings registered successfully";
	}
	
}
