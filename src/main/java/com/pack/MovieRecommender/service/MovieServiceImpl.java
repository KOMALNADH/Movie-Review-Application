package com.pack.MovieRecommender.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pack.MovieRecommender.Repository.UserRepository;
import com.pack.MovieRecommender.model.User;

@Service
@Transactional
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(user.getRoles());
		userRepo.save(user);
		
	}

	@Override
	public User findByUsername(String user) {
		User user1=userRepo.findByUser(user);
		return user1;
	}

	@Override
	public User findById(Long id) {
		Optional<User> opt=userRepo.findById(id);
		return opt.get();
	}
	

}
