package com.pack.MovieRecommender.controller;

import java.util.*;

import javax.validation.Valid;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pack.MovieRecommender.Repository.MovieDetailsRepository;
import com.pack.MovieRecommender.Repository.MovieTypeRepository;
import com.pack.MovieRecommender.Repository.RoleRepository;
import com.pack.MovieRecommender.Repository.UserRepository;
import com.pack.MovieRecommender.Validator.AddMovieValidator;
import com.pack.MovieRecommender.Validator.UserValidator;
import com.pack.MovieRecommender.model.MovieDetails;
import com.pack.MovieRecommender.model.MovieType;
import com.pack.MovieRecommender.model.Reviews;
import com.pack.MovieRecommender.model.Role;
import com.pack.MovieRecommender.model.User;
import com.pack.MovieRecommender.service.MovieDetailsService;
import com.pack.MovieRecommender.service.MovieService;
import com.pack.MovieRecommender.service.ReviewsService;



@Controller
public class MovieController {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	MovieService movieService;
	@Autowired
	UserValidator userValidator;
	@Autowired
	MovieDetailsService mdService;
	@Autowired
	MovieTypeRepository mtRepo;
	@Autowired
	MovieDetailsRepository mdRepo;
	@Autowired
	AddMovieValidator addMovieValidator;
	@Autowired
	ReviewsService reviewService;

	protected final static org.apache.juli.logging.Log logger=LogFactory.getLog(MovieController.class);



	//	@GetMapping("/")
//	public String welcome() {
//		
//		return "home";
//	}
	@GetMapping("/")
    public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");

		return modelAndView;
		
	}
 
	
	 @RequestMapping(value = "/registration", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new User());
	        model.addAttribute("roles", roleRepo.findAll());
	        return "registration";
	    }

	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
	    	userValidator.validate(userForm, bindingResult);
	    	if (bindingResult.hasErrors()) {
	    		ModelAndView modelAndView = new ModelAndView("registration");
	            return modelAndView;
	        }
	    	List<Role> roles=userForm.getRoles();
	        userForm.setRoles(roles);
	        movieService.save(userForm);
	        ModelAndView modelAndView = new ModelAndView("redirect:/login");
	        return modelAndView;
	    }

	@ModelAttribute("roleList")
	public Map<Integer,String> initializeRoles() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	map.put(1,"USER");
    	map.put(2,"ADMIN");
    	map.put(3,"CREATOR");
    	map.put(4,"EDITOR");
		return map;
	}
	
	@GetMapping("/login")
	public String login(Model model,String error,String logout,RedirectAttributes red) {
		if (error != null) 
    		
            model.addAttribute("error", "Your username and password is invalid.");
		
        if (logout !=null) 
        	model.addAttribute("message", "You have been logged out successfully.");      
        	
        return "login";
		
	}
	@GetMapping("/addMovie")
	public String addFilm(Model model) {
		model.addAttribute("MovieForm", new MovieDetails());
		model.addAttribute("movies", mtRepo.findAll());
		return "addFilm";
		
	}
	@PostMapping("/addMovie")
	public String addFilm(@ModelAttribute("MovieForm") MovieDetails MovieForm,Model model,BindingResult result) {
		addMovieValidator.validate(MovieForm, result);
		if(result.hasErrors()) {
			return "addFilm";
		}
		
		Integer roles=MovieForm.getMovieType().getId();
		Optional<MovieType> r=mtRepo.findById(roles);
		MovieForm.setMovieType(r.get());
		mdService.save(MovieForm);
		return "redirect:/";
		
		
	}
	@ModelAttribute("movieTypeList")
	public Map<Integer,String> initializeMovieTypes() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	map.put(1,"ANIME");
    	map.put(2,"HOLLYWOOD");
		return map;
	}
	@GetMapping("/getAnimeList")
	public String getAnimeList(Model model) {
		List<MovieDetails> list=mdService.fetchByMovieType(1);
		model.addAttribute("alist", list);
		return "animeList";
		
	}
	@GetMapping("/getAnime")
	public String getAnime(Model model) {
		List<MovieDetails> list=mdService.fetchByMovieType(1);
		model.addAttribute("alist", list);
		logger.info("anime list");
		return "anime";
		
	}
	@GetMapping("/getMovie")
	public String getMovie(Model model) {
		List<MovieDetails> list=mdService.fetchByMovieType(2);
		model.addAttribute("mlist", list);
		logger.info("Movie list");
		return "movie";
		
	}
	@GetMapping("/getMovieList")
	public String getMovieList(Model model) {
		List<MovieDetails> list=mdService.fetchByMovieType(2);
		model.addAttribute("hlist", list);
		return "MovieList";
	}
	@GetMapping("/403")
	public ModelAndView accessDenied() {
		ModelAndView modelAndView = new ModelAndView("accessDenied");
		return modelAndView;
	    
	}
	@GetMapping("/delete/{id}")
	public String deleteMovie(@PathVariable("id") Integer mid) {
		mdService.deleteById(mid);
		return "redirect:/";
		
	}
	@GetMapping("/edit/{id}")
	public String editProduct(Model model,@PathVariable("id") Integer mid) {
		MovieDetails md=mdService.fetchById(mid);
		model.addAttribute("editMovie", md);
		return "editMovie";
	}
//	@PostMapping("/edit")
//	public String editedProduct(@ModelAttribute("editProduct") MovieDetails md) {
//		mdService.save(md);
//		return "redirect:/";
//		
//	}
	@PostMapping("/edit")
    public String editedProduct(@ModelAttribute("editProduct") MovieDetails md) {
		mdService.save(md);
		return "redirect:/";
		
	}
	@GetMapping("/anime/{genre}")
	public String getAnimeByGenre(@PathVariable("genre") String genre,Model model) {
		List<MovieDetails> md=mdService.fetchByGenre(genre,1);
		model.addAttribute("alist", md);
		return "anime";
		
	}
	@GetMapping("/movie/{genre}")
	public String getMovieByGenre(@PathVariable("genre") String genre,Model model) {
		List<MovieDetails> md=mdService.fetchByGenre(genre,2);
		model.addAttribute("mlist", md);
		return "movie";
		
	}
	@GetMapping("/searchByName")
	public String searchByName(@RequestParam String display,Model model) {
		System.out.println(display);
		MovieDetails md=mdService.findByName(display);
		Integer id=md.getId();
		System.out.println(id);
		List<MovieDetails> md1=new ArrayList<>();
		md1.add(md);
		model.addAttribute("alist", md1);
		String url="redirect:/animeData/"+id;
		return "anime";
		
	}
//	@GetMapping("/anime/{genre}")
//	public String getAnimeByName(@PathVariable("genre") String genre,Model model) {
//		List<MovieDetails> md=mdService.fetchByGenre(genre,1);
//		model.addAttribute("alist", md);
//		return "anime";
//		
//	}
//	@GetMapping("/star")
//	public String getStarData(@RequestParam("rating") int value) {
//		System.out.println(value);
//		return "redirect:/403";
//	}
	
	@GetMapping("/animeData/{id}")
	public String getAnimeData(@PathVariable("id") Integer id,Model model) {
		MovieDetails md=mdService.fetchById(id);
		model.addAttribute("reviewForm", new Reviews());
		List<Reviews> list=reviewService.findById(id);
		model.addAttribute("reviewList", list);
		model.addAttribute("AnimeData",md);
		return "animeData";
		
	}
	
	@GetMapping("/movieData/{id}")
	public String getMovieData(@PathVariable("id") Integer id,Model model) {
		MovieDetails md=mdService.fetchById(id);
		model.addAttribute("reviewForm", new Reviews());
		List<Reviews> list=reviewService.findById(id);
		model.addAttribute("reviewList", list);
		model.addAttribute("MovieData",md);
		return "movieData";
		
	}
	 @RequestMapping(value = "/addReview/{id}", method = RequestMethod.POST)
	    public String saveReviews(@ModelAttribute("reviewForm") Reviews reviewForm, @PathVariable("id") Integer id, Model model) {
	     MovieDetails md=mdService.fetchById(id);   
		 reviewForm.setReviews(md);
		 	reviewService.saveReview(reviewForm);
	        return "redirect:/animeData/{id}";
	    }
	 @RequestMapping(value = "/addmovieReview/{id}", method = RequestMethod.POST)
	    public String saveMovieReviews(@ModelAttribute("reviewForm") Reviews reviewForm, @PathVariable("id") Integer id, Model model) {
	     MovieDetails md=mdService.fetchById(id);   
		 reviewForm.setReviews(md);
		 	reviewService.saveReview(reviewForm);
	        return "redirect:/movieData/{id}";
	    }
	
}
