package com.example.college.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.college.entity.Course;
import com.example.college.entity.StudentCourse;
import com.example.college.entity.User;
import com.example.college.services.InstructorService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	@Autowired
	InstructorService instructorService;

	@RequestMapping("/dashBoard")
	public String dashBoard(Model theModel)
	{
		List<Course> courses=instructorService.findCourses();
		theModel.addAttribute("courses",courses);
		return "instructor-page";
	}
	
	@RequestMapping("/showStudents")
	public String showStudents(@RequestParam("courseId") int id,Model theModel)
	{

        List<StudentCourse> studentCourse= instructorService.findStudents(id);
		theModel.addAttribute("studentCourse",studentCourse);
		return "students-course";
	}
	
	
	@RequestMapping("/showFormForAddGrade")
	public String addGrade(@RequestParam("studentId") int id,Model theModel)
	{

        StudentCourse studentCourse= instructorService.findStudentForAddGrade(id);
		theModel.addAttribute("studentCourse",studentCourse);

		return "add-grade-form";
	}
	
	@RequestMapping("/saveGrade")
	public String save(@ModelAttribute("studentCourse") StudentCourse studentuser)
	{
		instructorService.saveGrade(studentuser);
		return "redirect:/instructor/dashBoard";
	}
	
}
