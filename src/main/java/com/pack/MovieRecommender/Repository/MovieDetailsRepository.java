package com.pack.MovieRecommender.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.MovieRecommender.model.MovieDetails;

@Repository
public interface MovieDetailsRepository extends JpaRepository<MovieDetails,Integer>{
	MovieDetails findByName(String name);
	List<MovieDetails> findByGenre(String genre);
}
