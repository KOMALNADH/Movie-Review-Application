package com.pack.MovieRecommender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.MovieRecommender.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByUser(String user);
	User findByPassword(String Password);
	User findByUserAndPassword(String user,String password);

}
