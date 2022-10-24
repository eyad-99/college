package com.example.college.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.college.entity.Course;
import com.example.college.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.college.services.AdminService;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	AdminService adminServce;
	
	/*public AdminController(AdminService adminServce)
	{
		this.adminServce=adminServce;
	}*/

	@RequestMapping("/dashBoard")
	public String dashBoard(Model theModel)
	{
		List<User> users= adminServce.FindAllUsers();
		theModel.addAttribute("users",users);
		
		List<Course> courses= adminServce.FindAllCourses();
		theModel.addAttribute("courses",courses);
		
		return "admin-page";
	}
	
	@RequestMapping("/showFormForAddUser")
	public String showFormForAdd(Model theModel)
	{
		User user=new User();
		theModel.addAttribute("user",user);
		
	
		return "add-user-form";
	}
	
	
	
	@RequestMapping("/saveUser")
	public String showFormForAdd(@ModelAttribute("user") User user)
	{

		adminServce.save(user);
		return "redirect:/admin/dashBoard";
	}
	
	@RequestMapping("/showFormForUpdateUser")
	public String showFormForUpdate(@RequestParam("userId") int id,Model model)
	{
		User user=adminServce.findById(id);
		model.addAttribute("user",user);
		return "add-user-form"; 
	}
	
	
	@RequestMapping("/deleteUser")
	public String delete(@RequestParam("userId") int id)
	{
		adminServce.delete(id);
		
		return "redirect:/admin/dashBoard";	}
	
	
	
	@RequestMapping("/searchUser")
	public String delete(@RequestParam("userName") String name,Model model)
	{
		List<User> users=adminServce.searchBy(name);
		model.addAttribute("users",users);
		
		return "admin-page";
		}
	
	
	
	
	@RequestMapping("/showFormForAddCourse")
	public String showFormForAddCourse(Model theModel)
	{
		Course course=new Course();
		theModel.addAttribute("course",course);
		
	
		return "add-course-form";
	}
	
	
	@RequestMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course course )
	{
		
		System.out.println("theeeeeeeeeeeeeeeeeeeeeeeee ins id");

		System.out.println(course.getInstructor().getId());


		adminServce.saveCourse(course);
		return "redirect:/admin/dashBoard";
	}
	
	
	
	@RequestMapping("/deleteCourse")
	public String deleteCourse(@RequestParam("courseId") int id)
	{
		System.out.println("the id"+id);
		System.out.println(adminServce.findCourseById(id));


		adminServce.deleteCourse(id);
		
		return "redirect:/admin/dashBoard";
		}
	
	
	
	@RequestMapping("/searchCourse")
	public String searchCourse(@RequestParam("courseName") String name,Model model)
	{
		List<User> users= adminServce.FindAllUsers();
		model.addAttribute("users",users);
		List<Course> courses=adminServce.searchCourseBy(name);
		model.addAttribute("courses",courses);
		
		return "admin-page";
		}
	
	
	

	
	
	



@RequestMapping("/showFormForUpdateCourse")
public String showFormForUpdateCourse(@RequestParam("courseId") int id,Model model)
{
	
	Course course=adminServce.findCourseById(id);
	model.addAttribute("course",course);
	
	return "add-course-form"; 
}




}
	
	
	
	
	
	
	