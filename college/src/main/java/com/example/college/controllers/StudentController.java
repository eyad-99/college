package com.example.college.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.college.entity.Course;
import com.example.college.entity.StudentCourse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.college.services.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	

	

	@RequestMapping("/dashBoard")
	public String dashBoard(Model theModel)
	{
	//	System.out.println("this is current student student id is a     "+studentService.getCurrentStudent().get().getId());
	    List<StudentCourse> passedCourses = studentService.findPassedCourses2();
	    theModel.addAttribute("passedCourses",passedCourses);
	    List<StudentCourse> registeredCourses = studentService.registerdCourses();
	    theModel.addAttribute("registeredCourses",registeredCourses);
	    
	    List<StudentCourse> failedCourses = studentService.findFailedCourses();
	    theModel.addAttribute("failedCourses",failedCourses);
	    
	    List<Course> avilableCourses = studentService.availableCourses();
	    theModel.addAttribute("avilableCourses",avilableCourses);
	    
		return "student-page";
	}
	
	
/*	@RequestMapping("/registerForm")
	public String registerForm(Model theModel)
	{
		    List<Course> availableCourses=studentService.availableCourses();
		  //  for(Course course:availableCourses)
			//System.out.println("this is available course     " +course.getId());
		    theModel.addAttribute("availableCourses",availableCourses);
		     return "register-form";
	}*/
	
	
	@RequestMapping("/deleteRegistered")
	public String deleteRegisterdCourses(@RequestParam("courseId") int id)
	{
		   studentService.deleteRegistered(id);
		   return  "redirect:/student/dashBoard";

	}
	
	
	@RequestMapping("/register")
	public String registerCourses(@RequestParam("courseId") int id)
	{
		   studentService.register(id);
		   return  "redirect:/student/dashBoard";

	}
	
	

}
