package com.pack.MovieRecommender;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.MovieRecommender.Repository.MovieDetailsRepository;
import com.pack.MovieRecommender.Repository.MovieTypeRepository;
import com.pack.MovieRecommender.Repository.ReviewsRepository;
import com.pack.MovieRecommender.Repository.RoleRepository;
import com.pack.MovieRecommender.Repository.UserRepository;
import com.pack.MovieRecommender.Validator.AddMovieValidator;
import com.pack.MovieRecommender.Validator.UserValidator;
import com.pack.MovieRecommender.controller.MovieController;
import com.pack.MovieRecommender.model.MovieDetails;
import com.pack.MovieRecommender.model.MovieType;
import com.pack.MovieRecommender.model.Reviews;
import com.pack.MovieRecommender.model.Role;
import com.pack.MovieRecommender.service.MovieDetailsServiceImpl;
import com.pack.MovieRecommender.service.MovieServiceImpl;
import com.pack.MovieRecommender.service.ReviewsServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieRecommenderControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	MovieDetailsServiceImpl mdService;
	@MockBean
	MovieServiceImpl movieService;
	@MockBean
	ReviewsServiceImpl reviewService;
	@MockBean
	MovieDetailsRepository mdRepo;
	@MockBean
	MovieTypeRepository mtRepo;
	@MockBean
	ReviewsRepository reviewRepo;
	@MockBean
	RoleRepository roleRepo;
	@MockBean
	UserRepository userRepo;
	@MockBean
	AddMovieValidator addMovieValidator;
	@MockBean
	UserValidator userValidator;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@InjectMocks
    private MovieController movieController;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void add() {
		assertEquals(5, 5);
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}

	@Test
//	@Disabled
	public void testAccessDenied() throws Exception {
		MovieController controller = new MovieController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(MockMvcRequestBuilders.get("/403")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("accessDenied"));
	}

	@Test
	public void testRegistration() throws Exception {
		mockMvc.perform(get("/registration")).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("registration")).andExpect(model().attributeExists("userForm"));
	}

	@Test
	public void testHomePage() throws Exception {
		MovieController controller = new MovieController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.model().attributeExists("modelAttribute"))
				.andExpect(MockMvcResultMatchers.view().name("home"));
	}


	@Test
	@Disabled
	public void save() throws Exception {
		MovieController controller = new MovieController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		Role r = new Role(1l, "admin");
		Role r1 = new Role(2l, "user");
		List<Role> list = new ArrayList<>();
		list.add(r1);
		mockMvc.perform(MockMvcRequestBuilders.post("/registration").param("id", "1l").param("user", "Heater")
				.param("password", "12345678").param("passwordConfirm", "12345678").param("roles", "list"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/login"));

//        ArgumentCaptor<User> argCap=ArgumentCaptor.forClass(User.class);
//        Mockito.verify(movieService).save(argCap.capture());
//        assertEquals("Heater",argCap.getValue().getUser());
	}
	

	
	@Test
	void getAllAnimeList() throws Exception {
		List<MovieDetails> md = new ArrayList<>();
		MovieDetails md1 = new MovieDetails(1, "king", 9.0, "action", "nice one", "img.jpg", new MovieType(1, "anime"));
		MovieDetails md2 = new MovieDetails(2, "queen", 8.5, "romance", "beautiful", "img.jpg", new MovieType(2, "hollywood"));
		md.add(md1);
		md.add(md2);
		Mockito.when(mdService.fetchByMovieType(1)).thenReturn(md);
		mockMvc.perform(get("/getAnimeList")).andExpect(status().isOk())
//            .andExpect(view().name("redirect:/login"))
            .andExpect(model().attributeExists("alist")).andReturn();
	
	}
	@Test
	void testDeteleById() throws Exception{
//		Integer id=1;
//		Student s=new Student(id,"komal","cse",8775l);
//		when(studentRepo.findById(id)).thenReturn(Optional.of(s));
//		mockMvc.perform(delete("/student/delete/{id}",id))
//		       .andExpect(status().isOk());
	MovieDetails md=new MovieDetails(1,"komal",9.0,"actoion","good","img.jpg", new MovieType(1,"anime"));
	Mockito.when(mdService.save(md)).thenReturn(md);
	mockMvc.perform(get("/delete/{id}",1))
		.andExpect(status().is3xxRedirection());
	verify(mdService,times(1)).deleteById(1);
	}
	@Test
	void testGetAnimeByGerner() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		MovieDetails md1=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchByGenre("action", 1)).thenReturn(list);
		mockMvc.perform(get("/anime/{genre}","action"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("alist"))
				.andExpect(view().name("anime"));
		
	}
	@Test
	void testGetMovieByGerner() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		MovieDetails md1=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchByGenre("action", 1)).thenReturn(list);
		mockMvc.perform(get("/movie/{genre}","action"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("mlist"))
				.andExpect(view().name("movie"));
		
	}
	@Test
	void testGetAnimeData() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		MovieDetails md1=new MovieDetails(2,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchById(1)).thenReturn(md);
		mockMvc.perform(get("/animeData/{id}",1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("AnimeData"))
			.andExpect(view().name("animeData"));
	}
	@Test
	void testGetMovieData() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		MovieDetails md1=new MovieDetails(2,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchById(1)).thenReturn(md);
		mockMvc.perform(get("/movieData/{id}",1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("MovieData"))
			.andExpect(view().name("movieData"));
	}
	@Test
	void testGetAnimeList() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		MovieDetails md1=new MovieDetails(2,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchByMovieType(1)).thenReturn(list);
		mockMvc.perform(get("/getAnimeList"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("alist"))
			.andExpect(view().name("animeList"));
	}
	@Test
	void testGetMovieList() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		MovieDetails md1=new MovieDetails(2,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchByMovieType(2)).thenReturn(list);
		mockMvc.perform(get("/getMovieList"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("hlist"))
			.andExpect(view().name("MovieList"));
	}
	@Test 
	void testGetAnime() throws Exception{
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		MovieDetails md1=new MovieDetails(2,"komal",9.0,"action","good","img.jpg", new MovieType(1,"anime"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchByMovieType(1)).thenReturn(list);
		Mockito.when(mdService.fetchByMovieType(1)).thenReturn(list);
		mockMvc.perform(get("/getAnime"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("alist"))
			.andExpect(view().name("anime"));
	}
	@Test
	void testGetMovie() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		MovieDetails md1=new MovieDetails(2,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		List<MovieDetails> list=new ArrayList<>();
		list.add(md1);
		list.add(md);
		Mockito.when(mdService.save(md1)).thenReturn(md1);
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchByMovieType(2)).thenReturn(list);
		mockMvc.perform(get("/getMovie"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("mlist"))
			.andExpect(view().name("movie"));
	}
	@Test
//	@Disabled
	void testEditProduct() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		Mockito.when(mdService.save(md)).thenReturn(md);
		Mockito.when(mdService.fetchById(1)).thenReturn(md);
		mockMvc.perform(get("/edit/{id}",1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("editMovie"))
			.andExpect(view().name("editMovie"));
	}

	@Test
//	@Disabled
	void testEditedProduct() throws Exception {
		MovieDetails md=new MovieDetails(1,"komal",9.0,"action","good","img.jpg", new MovieType(2,"Hollywood"));
		Mockito.when(mdService.save(md)).thenReturn(md);
		mockMvc.perform(post("/edit"))
		.andExpect(status().is4xxClientError())
	;
		
                

	}
	
}
