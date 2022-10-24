package com.example.college.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.college.dao.CourseRepository;
import com.example.college.dao.InstructorRepository;
import com.example.college.dao.StudentRepository;
import com.example.college.dao.UserRepository;
import com.example.college.entity.Course;
import com.example.college.entity.Instructor;
import com.example.college.entity.Student;
import com.example.college.entity.User;

@Service
public class AdminServiceImpl implements AdminService {
	
	private String rs="ROLE_STUDENT";
	private String ri="ROLE_Instructor";

	
	@Autowired
	private UserRepository ur;
	
	
	@Autowired
	private CourseRepository cr;
	
	
	@Autowired
	private StudentRepository sr;
	
	@Autowired
	private InstructorRepository ir;
	
	@Override
	public List<User> FindAllUsers()
	{
		List<User> users=ur.findAll();
		return users;
	}
	
	@Override
	public void save(User user)
	{
		
		ur.save(user); 		
		if((user.getRole()).equals(rs))
		{
			Student s=new Student();
			s.setId(user.getId());
			user.setStudent(s);
			s.setUser(user);
			//user.setInstructor(null);
			Optional<Instructor> result = ir.findById(user.getId());
			if(result.isPresent())
			ir.deleteById(user.getId());
		}
		else
		{	
			Instructor i=new Instructor();
			i.setId(user.getId());
			user.setInstructor(i);
			i.setUser(user);
		//	user.setStudent(null);
			Optional<Student> result = sr.findById(user.getId());
			if(result.isPresent())
			sr.deleteById(user.getId());
		}
		ur.save(user);	
		
	}
	
	
	
	
	
	public User findById(int id)
	{
            Optional<User> result = ur.findById(id);
		    User user = null;
		
		   if (result.isPresent())
		   {
			   user = result.get();
	    	}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find employee id - " + id);
		    }
		
		return user ;	
		
	}
	
	public void delete(int id)
	{
		
		if(ur.getById(id).getInstructor() != null)
		{
			Instructor instructor =ur.getById(id).getInstructor();
			List<Course> courses=instructor.getCourses();
			for(Course course:courses)
			{
				course.setInstructor(ir.getById(2));
				cr.save(course);
			}
		}
		ur.deleteById(id);
	}
	
	
	

	@Override
	public List<User> searchBy(String theName) {
		
		List<User> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			results = ur.findByNameContainsAllIgnoreCase(theName);
		}
		else {
			results = FindAllUsers();
		}
		
		return results;
	}
	
	
	
	
	@Override
	public List<Course> FindAllCourses()
	{
		List<Course> courses=cr.findAll();
		return courses;
	}
	
	
	@Override
	public void saveCourse(Course course)
	{
		cr.save(course);
		//System.out.println("print instructor id for the course");

		//System.out.println(course.getInstructor().getId());
	}
	
	
	@Override
	public void deleteCourse(int id)

	{
		System.out.println("this is course id we want to delete"+ id);
		Course course=findCourseById(id);
		course.getInstructor().delete(course);
		
		
		System.out.println("this is instructor id we want to delete"+ course.getInstructor().getId());

		cr.delete(course);
	}
	
	
	@Override
	public Course findCourseById(int id)
	{

        Optional<Course> result = cr.findById(id);
	    Course course = null;
	
	   if (result.isPresent())
	   {
		   course = result.get();
    	}
	else {
		// we didn't find the user
		throw new RuntimeException("Did not find employee id - " + id);
	    }
	
	return course ;	
	}
	
	
	
	@Override
	public List<Course> searchCourseBy(String theName) {
		
		List<Course> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			results = cr.findByNameContainsAllIgnoreCase(theName);
		}
		else {
			results = FindAllCourses();
		}
		
		return results;
	}


	
	



	
	
	

}
