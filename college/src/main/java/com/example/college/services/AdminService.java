package com.example.college.services;

import java.util.List;
import java.util.Optional;

import com.example.college.entity.Course;
import com.example.college.entity.User;

public interface AdminService {

	public List<User> FindAllUsers();
	

	public void save(User user);
	
	public User findById(int theId);    
	
	
	
	public void delete(int theId);


	List<User> searchBy(String theName);


	List<Course> FindAllCourses();


	void saveCourse(Course course);


	void deleteCourse(int id); 
	
	
	public Course findCourseById(int theId);


	List<Course> searchCourseBy(String theName); 
}
	
