package com.example.college.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.college.entity.StudentCourse;
import com.example.college.entity.Course;
import com.example.college.entity.Student;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer>{
   public List<StudentCourse> findByPassed(int passed);
	public List<StudentCourse> findByPassedAndStudent(int passed,Student student);
	public List<StudentCourse> findByGradeAndStudent(int passed,Student student);
	public List<StudentCourse> findByGradeGreaterThanEqualAndStudent(int grade,Student student);
	public List<StudentCourse> findByGradeGreaterThanEqualAndGradeLessThanEqualAndStudent(int grade1,int grade2,Student student);
	public StudentCourse findByStudentAndCourse(Student student,Course course);
	
	public List<StudentCourse> findByGradeAndCourse(int grade,Course course);



}