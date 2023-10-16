package com.pack.MovieRecommender.model;


import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userDetails")
public class User {
    private Long id;
    private String user;
    private String password;
    private String passwordConfirm;
    private List<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", roles=" + roles + "]";
	}

	public User(Long id, String user, String password, String passwordConfirm, List<Role> roles) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.roles = roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
    
}
