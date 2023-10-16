package com.pack.MovieRecommender.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.MovieRecommender.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	List<Role> findAllById(Long id);
}
