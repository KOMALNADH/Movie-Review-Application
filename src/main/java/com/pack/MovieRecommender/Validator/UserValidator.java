package com.pack.MovieRecommender.Validator;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pack.MovieRecommender.model.User;
import com.pack.MovieRecommender.service.MovieService;

@Component
@Transactional
public class UserValidator implements Validator{
	
	
	@Autowired
	MovieService movieService;
	 
		@Override
	    public boolean supports(Class<?> aClass) {
	        return User.class.equals(aClass);
	    }

	    @Override
	    public void validate(Object o, Errors errors) {
	        User user = (User) o;

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "NotEmpty");
	        if (user.getUser().length() < 4 || user.getUser().length() > 32) {
	            errors.rejectValue("user", "Size.userForm.username");
	        }
	        if (movieService.findByUsername(user.getUser()) != null) {
	            errors.rejectValue("user", "Duplicate.userForm.username");
	        }

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
	        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
	            errors.rejectValue("password", "Size.userForm.password");
	        }

	        if (!user.getPasswordConfirm().equals(user.getPassword())) {
	            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
	        }
	    }



	

}
