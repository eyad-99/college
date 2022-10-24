package com.example.college.services;

import java.util.List;
import java.util.Optional;

import com.example.college.entity.Course;
import com.example.college.entity.Instructor;
import com.example.college.entity.StudentCourse;
import com.example.college.entity.User;

public interface InstructorService {

	Optional<Instructor> getCurrentInstructor();

	List<Course> findCourses();

	List<StudentCourse> findStudents(int id);

	StudentCourse findStudentForAddGrade(int id);

	void saveGrade(StudentCourse studentCourse);

	
}
	
