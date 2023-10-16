package com.pack.MovieRecommender.service;

import java.util.List;

import com.pack.MovieRecommender.model.Reviews;

public interface ReviewsService {
	public void saveReview(Reviews review);
	List<Reviews> findById(Integer id);
	Reviews fetchById(Integer id);
}
