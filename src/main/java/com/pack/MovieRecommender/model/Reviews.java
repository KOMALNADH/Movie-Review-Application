package com.pack.MovieRecommender.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reviews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String userName;
	private Integer rating;
	private String comment;
	@ManyToOne
	@JoinColumn(name="movie_id")
	private MovieDetails reviews;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MovieDetails getReviews() {
		return reviews;
	}
	public void setReviews(MovieDetails reviews) {
		this.reviews = reviews;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Reviews(Integer id, String userName, Integer rating, String comment, MovieDetails reviews) {
		super();
		this.id = id;
		this.userName = userName;
		this.rating = rating;
		this.comment = comment;
		this.reviews = reviews;
	}
	public Reviews() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Reviews [id=" + id + ", userName=" + userName + ", rating=" + rating + ", comment=" + comment
				+ ", reviews=" + reviews + "]";
	}
	
}
