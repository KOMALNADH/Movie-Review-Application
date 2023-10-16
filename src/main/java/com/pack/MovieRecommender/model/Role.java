package com.pack.MovieRecommender.model;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    private Long id;
    private String name;
    private List<User> users;

    @Id
    @Column(name="role_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    

    @ManyToMany
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
