package com.pack.MovieRecommender.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                    .antMatchers("/resources/**", "/registration","/WEB-INF/jsp/**").permitAll()
	                    .antMatchers("/addMovie").hasAnyAuthority("creator", "editor", "admin")
	        			.antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
	        			.antMatchers("/edit/**").hasAnyAuthority("admin", "editor")
	        			.antMatchers("/delete/**").hasAuthority("admin")
	                    .anyRequest().authenticated()
	                    .and()
	                .formLogin()
	                    .loginPage("/login")
	                    .permitAll()
	                    .and()
	                .logout()
	                    .permitAll()
	                    .and()
	        			.exceptionHandling().accessDeniedPage("/403");

	    }
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }
}
