package com.pack.MovieRecommender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.MovieRecommender.model.MovieType;

@Repository
public interface MovieTypeRepository extends JpaRepository<MovieType,Integer>{

}
