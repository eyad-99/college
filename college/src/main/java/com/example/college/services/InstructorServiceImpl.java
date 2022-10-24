package com.example.college.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.college.dao.CourseRepository;
import com.example.college.dao.InstructorRepository;
import com.example.college.dao.StudentCourseRepository;
import com.example.college.dao.StudentRepository;
import com.example.college.dao.UserRepository;
import com.example.college.entity.Course;
import com.example.college.entity.Instructor;
import com.example.college.entity.Student;
import com.example.college.entity.StudentCourse;
import com.example.college.entity.User;

@Service
public class InstructorServiceImpl implements InstructorService {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentCourseRepository studentCourseRepository;
	
	@Autowired
	InstructorRepository instructorRepository;
	
	@Autowired
	UserRepository ur;
	
	
	@Override
	public Optional<Instructor> getCurrentInstructor()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<User> user=ur.findByEmail(userDetails.getUsername());
		return instructorRepository.findById(user.get(0).getId()) ;

	}
	
	@Override
	public List<Course> findCourses()
	{
		return courseRepository.findByInstructor(getCurrentInstructor().get());

	}
	
	@Override
	public List<StudentCourse> findStudents(int id)
	{
		Course course= courseRepository.getById(id);
		
		return studentCourseRepository.findByGradeAndCourse(-1, course);

	}
	
	@Override
	public StudentCourse findStudentForAddGrade(int id)
	{
		return studentCourseRepository.getById(id);
	}
	
	
	@Override
	public void saveGrade(StudentCourse studentCourse) 
	{
		 studentCourseRepository.save(studentCourse);
	}

	

}
