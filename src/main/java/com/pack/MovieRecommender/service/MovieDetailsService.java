package com.pack.MovieRecommender.service;

import java.util.List;

import com.pack.MovieRecommender.model.MovieDetails;

public interface MovieDetailsService {
	MovieDetails save(MovieDetails movieDetails);
	List<MovieDetails> fetchByMovieType(Integer id);
	void deleteById(Integer id);
	public MovieDetails fetchById(Integer id);
	public MovieDetails findByName(String name);
	List<MovieDetails> fetchByGenre(String genre,Integer id);
}
