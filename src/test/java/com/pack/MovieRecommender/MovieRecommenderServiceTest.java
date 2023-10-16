package com.pack.MovieRecommender;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.MovieRecommender.Repository.MovieDetailsRepository;
import com.pack.MovieRecommender.Repository.MovieTypeRepository;
import com.pack.MovieRecommender.Repository.RoleRepository;
import com.pack.MovieRecommender.model.MovieDetails;
import com.pack.MovieRecommender.model.MovieType;
import com.pack.MovieRecommender.model.Reviews;
import com.pack.MovieRecommender.model.Role;
import com.pack.MovieRecommender.model.User;
import com.pack.MovieRecommender.service.MovieDetailsService;
import com.pack.MovieRecommender.service.MovieService;
import com.pack.MovieRecommender.service.ReviewsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRecommenderServiceTest {
	@Autowired
	MovieService movieService;
	
	@Autowired
	MovieDetailsService mdService;
	
	@Autowired
	ReviewsService reviewsService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	MovieTypeRepository mtRepo;
	@Autowired
	MovieDetailsRepository mdRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	@Disabled
	void testSave() {
		Optional<Role> opt=roleRepo.findById(1l);
		List<Role> list=roleRepo.findAllById(1l);
		User user=new User();
		user.setId(7l);
		user.setPassword(bCryptPasswordEncoder.encode("abcd"));
		user.setPasswordConfirm("abcd");
		user.setUser("king");
		user.setRoles(list);;
        movieService.save(user);
        User data = movieService.findById(user.getId());
        assertEquals(user.getId(),data.getId());
	}
	@Test
	void testFindByName() {
		User user=movieService.findByUsername("komal");
		assertEquals(user.getUser(),"komal");
	}
	@Test
	void testFindById() {
		User user=movieService.findById(1l);
		assertEquals(user.getId(),(Long)1l);
	}
	@Test
	@Disabled
	void testSaveMovieDetails() {
		Optional<MovieType> opt=mtRepo.findById(2);
		MovieDetails md=new MovieDetails();
		md.setId(25);
		md.setName("The Flash");
		md.setGenre("Adventure");
		md.setRating(9.0);
		md.setLink("https://i.pinimg.com/474x/31/14/9e/31149ee38ed55c551d080ffc7a1d48a5.jpg");
		md.setDescription("Nicknamed \"the Scarlet Speedster\", all incarnations of the Flash possess \"superspeed\", which includes the ability to run, move, and think extremely fast, use superhuman reflexes, and seemingly violate certain laws of physics.");
		md.setMovieType(opt.get());
		mdService.save(md);
		MovieDetails opt1=mdService.fetchById(md.getId());
		assertEquals(md.getId(),opt1.getId());
		
	}
	@Test
	void testGetMovieDetailsByMovieType() {
		List<MovieDetails> md=mdRepo.findAll();
			List<MovieDetails> mdAnime=mdService.fetchByMovieType(1);
			List<MovieDetails> mdHollywood=mdService.fetchByMovieType(2);
			assertEquals(md.size(),mdAnime.size()+mdHollywood.size());
	
	}
	@Test
	@Disabled
	void testDeleteById() {
		List<MovieDetails> md=mdRepo.findAll();
		mdService.deleteById(25);
		List<MovieDetails> md1=mdRepo.findAll();
		assertEquals(md1.size(),md.size()-1);
	}
	@Test
	@Disabled
	void testFetchById() {
		MovieDetails md=mdService.fetchById(28);
		assertEquals(md.getId(),(Integer)28);
	}
	@Test
	void testFindByGenre() {
		Integer Acount = 0;
		Integer Hcount=0;
		List<MovieDetails> Anime=mdService.fetchByGenre("Action", 1);
		List<MovieDetails> Hollywood=mdService.fetchByGenre("Action", 2);
		for(MovieDetails md:Anime) {
			if(md.getGenre().equalsIgnoreCase("Action")) {
				Acount++;
			}
		}
		for(MovieDetails md:Hollywood) {
			if(md.getGenre().equalsIgnoreCase("Action")) {
				Hcount++;
			}
		}
		Integer AnimeSize=Anime.size();
		Integer HSize=Hollywood.size();
		assertEquals(AnimeSize,Acount);
		assertEquals(HSize,Hcount);
	}
	@Test
	void testgetMovieByName() {
		MovieDetails md=mdService.findByName("Avengers");
		assertEquals(md.getName(),"Avengers");
	}
	@Test
	void testSaveReview() {
		MovieDetails md=mdService.fetchById(14);
		Reviews review=new Reviews();
		review.setId(7);
		review.setRating(5);
		review.setUserName("komalnadh");
		review.setComment("wonderful movie");
		review.setReviews(md);
		reviewsService.saveReview(review);
		Reviews r=reviewsService.fetchById(7);
		assertEquals(r.getId(),review.getId());
	}
	@Test
	void testToGetListOfReviewsWithId() {
		List<Reviews> list=reviewsService.findById(19);
		List<Reviews> l=new ArrayList<>();
		for(Reviews r:list) {
			l.add(r);
		}
		assertEquals(list.size(),l.size());
		
	}
	@Test
	void testToGetReviewbyId() {
		Reviews r=reviewsService.fetchById(5);
		assertEquals(r.getId(),(Integer)5);
	}
}
