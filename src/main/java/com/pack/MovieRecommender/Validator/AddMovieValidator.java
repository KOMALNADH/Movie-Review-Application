package com.pack.MovieRecommender.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pack.MovieRecommender.model.MovieDetails;
import com.pack.MovieRecommender.service.MovieDetailsService;
@Component
public class AddMovieValidator implements Validator{

	@Autowired
	MovieDetailsService mdService;
	@Override
    public boolean supports(Class<?> aClass) {
        return MovieDetails.class.equals(aClass);
    }

	 @Override
	    public void validate(Object o, Errors errors) {
//	        User user = (User) o;
	        MovieDetails md=(MovieDetails) o;
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
	        if (md.getName().length() < 4 || md.getName().length() > 32) {
	            errors.rejectValue("name", "Size.MovieForm.name");
	        }
	        if (mdService.findByName(md.getName()) != null) {
	            errors.rejectValue("name", "Duplicate.MovieForm.name");
	        }

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rating", "NotEmpty");
	        if (md.getRating() ==null ) {
	            
	        }
	        else if(md.getRating()<1.0 || md.getRating()>10){
	        	errors.rejectValue("rating", "Rating.MovieForm.rating");
	        }
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "NotEmpty");
	        if (md.getGenre()==null) {
	            
	        }
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
	        if (md.getDescription() ==null) {
	            
	        }
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "link", "NotEmpty");
	        if (md.getLink()==null) {
	            
	        }
	        
	    }

}
