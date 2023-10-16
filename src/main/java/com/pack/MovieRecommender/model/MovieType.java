package com.pack.MovieRecommender.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class MovieType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String movieType;
	@OneToMany(mappedBy="movieType")
	private List<MovieDetails> movies=new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMovieType() {
		return movieType;
	}
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	public List<MovieDetails> getMovies() {
		return movies;
	}
	public void setMovies(List<MovieDetails> movies) {
		this.movies = movies;
	}
	public MovieType(Integer id, String movieType) {
		super();
		this.id = id;
		this.movieType = movieType;
		this.movies = movies;
	}
	@Override
	public String toString() {
		return "MovieType [id=" + id + ", movieType=" + movieType + ", movies=" + movies + "]";
	}
	public MovieType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
