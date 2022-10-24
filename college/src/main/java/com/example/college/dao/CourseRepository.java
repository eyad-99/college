package com.example.college.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.college.entity.Course;
import com.example.college.entity.Instructor;

public interface CourseRepository  extends JpaRepository<Course, Integer> {
	
	
	public List<Course>findByNameContainsAllIgnoreCase(String name);
	public List<Course>findByInstructor(Instructor instructor);


}
