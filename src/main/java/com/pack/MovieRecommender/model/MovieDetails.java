package com.pack.MovieRecommender.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.*;

@Entity
public class MovieDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double rating;
	private String genre;
	private String description;
	private String link;
	@OneToMany(mappedBy="reviews")
	private List<Reviews> reviews=new ArrayList<>();
	@ManyToOne
	@JoinColumn(name="movieType_id")
	private MovieType movieType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public MovieType getMovieType() {
		return movieType;
	}
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Reviews> getReviews() {
		return reviews;
	}
	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}
	
	public MovieDetails(Integer id, String name, Double rating, String genre, String description, String link,
			MovieType movieType) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.genre = genre;
		this.description = description;
		this.link = link;
		this.movieType = movieType;
	}
	public MovieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MovieDetails [id=" + id + ", name=" + name + ", rating=" + rating + ", genre=" + genre
				+ ", description=" + description + ", link=" + link + ", reviews=" + reviews + ", movieType="
				+ movieType + "]";
	}
	
	
}
