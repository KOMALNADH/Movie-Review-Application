package com.pack.MovieRecommender.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.MovieRecommender.Repository.MovieDetailsRepository;
import com.pack.MovieRecommender.model.MovieDetails;

@Service
@Transactional
public class MovieDetailsServiceImpl implements MovieDetailsService{
	@Autowired	
	MovieDetailsRepository mdRepo;

	@Override
	public MovieDetails save(MovieDetails movieDetails) {
		return mdRepo.save(movieDetails);
		
	}

	@Override
	public List<MovieDetails> fetchByMovieType(Integer id) {
		List<MovieDetails> list=mdRepo.findAll();
		List<MovieDetails> list1=new ArrayList<>();
		List<MovieDetails> hlist=new ArrayList<>();
		for(MovieDetails md:list) {
			if(md.getMovieType().getId()==1) {
				list1.add(md);
			}
			else if(md.getMovieType().getId()==2) {
				hlist.add(md);
			}
		}
		if(id==1) {
		return list1;
		}
		else {
			return hlist;
		}
	}

	@Override
	public void deleteById(Integer id) {
		mdRepo.deleteById(id);
		
	}

	@Override
	public MovieDetails fetchById(Integer id) {
		Optional<MovieDetails> opt=mdRepo.findById(id);
		return opt.get();
	}

	@Override
	public MovieDetails findByName(String name) {
		MovieDetails md=mdRepo.findByName(name);
		return md;
	}

	@Override
	public List<MovieDetails> fetchByGenre(String genre,Integer id) {
		List<MovieDetails> list=mdRepo.findByGenre(genre);
		List<MovieDetails> list1=new ArrayList<>();
		for(MovieDetails md:list) {
			if(md.getMovieType().getId()==id){
				list1.add(md);
			}
		}
		return list1;
	}
	
}
