package com.example.college.services;

import java.util.List;
import java.util.Optional;

import com.example.college.entity.Course;
import com.example.college.entity.Student;
import com.example.college.entity.StudentCourse;
import com.example.college.entity.User;


public interface StudentService {
	
	
//	public List<StudentCourse>findPassed(int passed);
	public Optional<Student> getCurrentStudent();
	//List<StudentCourse> findPassedCourses(int passed, Optional<Student> optional);
	//List<StudentCourse> findPassedCourses(int passed, Student student);

	List<Course> availableCourses();
	List<StudentCourse> registerdCourses();
	List<StudentCourse> findPassedCourses2();
	List<StudentCourse> findFailedCourses();

	void deleteRegistered(int courseId);

	void register(int courseId);

}
