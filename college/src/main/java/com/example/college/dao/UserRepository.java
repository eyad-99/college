package com.example.college.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.college.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByNameContainsAllIgnoreCase(String Name);
	
	List<User> findByEmail(String email);

}