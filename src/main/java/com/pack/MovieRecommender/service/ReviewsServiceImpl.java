package com.pack.MovieRecommender.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.MovieRecommender.Repository.ReviewsRepository;
import com.pack.MovieRecommender.model.Reviews;
@Service
@Transactional
public class ReviewsServiceImpl implements ReviewsService{
	
	@Autowired
	ReviewsRepository reviewRepo;

	@Override
	public void saveReview(Reviews review) {
		reviewRepo.save(review);
		
	}

	@Override
	public List<Reviews> findById(Integer id) {
		List<Reviews> list=reviewRepo.findAll();
		List<Reviews> list1=new ArrayList<>();
		for(Reviews r:list) {
			if(r.getReviews().getId()==id) {
				list1.add(r);
			}
	
		}
		return list1;
	}

	@Override
	public Reviews fetchById(Integer id) {
		Optional<Reviews> opt=reviewRepo.findById(id);
		return opt.get();
	}

}
