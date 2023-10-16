package com.pack.MovieRecommender;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.MovieRecommender.Repository.MovieDetailsRepository;
import com.pack.MovieRecommender.Repository.MovieTypeRepository;
import com.pack.MovieRecommender.Repository.ReviewsRepository;
import com.pack.MovieRecommender.Repository.RoleRepository;
import com.pack.MovieRecommender.Repository.UserRepository;
import com.pack.MovieRecommender.model.MovieDetails;
import com.pack.MovieRecommender.model.MovieType;
import com.pack.MovieRecommender.model.Role;
import com.pack.MovieRecommender.model.User;

import static org.junit.Assert.assertEquals;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRecommenderDAOTest {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	MovieDetailsRepository mdRepo;
	@Autowired
	MovieTypeRepository mtRepo;
	@Autowired
	ReviewsRepository reviewRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	@Disabled
	void saveUserTest(){
		Optional<Role> opt=roleRepo.findById(1l);
		List<Role> list=roleRepo.findAllById(1l);
		User user=new User();
		user.setId(8l);
		user.setPassword(bCryptPasswordEncoder.encode("abcd"));
		user.setPasswordConfirm("abcd");
		user.setUser("Jammy");
		user.setRoles(list);;
        User savedInDb = userRepo.save(user);
        Optional<User> data = userRepo.findById(savedInDb.getId());
        User getFromDb = (User)data.get();
        assertEquals(savedInDb.getId(),getFromDb.getId());
}
	@Test
	void testfindByUserId() {
		Optional<User> opt=userRepo.findById(5l);
		User user=opt.get();
		assertEquals(user.getId(),(Long)5l);
	}
	@Test
	void testFindByUserName() {
		User user=userRepo.findByUser("komal");
		assertEquals(user.getUser(),"komal");
	}
	@Test 
	@Disabled
	void testGetMovies() {
		List<MovieDetails> md=mdRepo.findAll();
		List<MovieDetails> list=new ArrayList<>();
		for(MovieDetails m:md) {
			list.add(m);
		}
		assertEquals(list.size(),md.size());
	}
	@Test
	@Disabled
	void testSaveMovie() {
		Optional<MovieType> opt=mtRepo.findById(2);
		MovieDetails md=new MovieDetails();
		md.setId(23);
		md.setName("Game Of Thrones");
		md.setGenre("Adventure");
		md.setRating(9.0);
		md.setLink("https://m.media-amazon.com/images/I/8154BRJVOVL._AC_UF1000,1000_QL80_.jpg");
		md.setDescription("Game of Thrones is an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO. It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin, the first of which is A Game of Thrones.");
		md.setMovieType(opt.get());
		MovieDetails saveInDb=mdRepo.save(md);
		Optional<MovieDetails> opt1=mdRepo.findById(saveInDb.getId());
		MovieDetails getFromDb=opt1.get();
		assertEquals(saveInDb.getId(),getFromDb.getId());
		
	}
	@Test
	void testFindMovieById() {
		Optional<MovieDetails> opt=mdRepo.findById(3);
		MovieDetails md=opt.get();
		assertEquals(md.getId(),(Integer)3);
	}
	@Test
	void testgetMovieByName() {
		MovieDetails md=mdRepo.findByName("Avengers");
		assertEquals(md.getName(),"Avengers");
	}
	
}
