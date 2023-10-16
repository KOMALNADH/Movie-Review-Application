package com.pack.MovieRecommender.service;

import com.pack.MovieRecommender.model.User;

public interface MovieService {
	public void save(User user);
	public User findByUsername(String user);
	public User findById(Long id);
}
