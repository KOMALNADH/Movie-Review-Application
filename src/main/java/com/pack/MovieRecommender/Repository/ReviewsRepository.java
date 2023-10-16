package com.pack.MovieRecommender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.MovieRecommender.model.Reviews;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Integer>{

}
