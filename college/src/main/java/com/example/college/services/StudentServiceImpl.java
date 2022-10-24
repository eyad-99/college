package com.example.college.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.college.dao.CourseRepository;
import com.example.college.dao.StudentCourseRepository;
import com.example.college.dao.StudentRepository;
import com.example.college.entity.Course;
import com.example.college.entity.Student;
import com.example.college.entity.StudentCourse;
import com.example.college.entity.User;
import com.example.college.dao.UserRepository;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentCourseRepository studentCourseReopsitory;
	@Autowired
	UserRepository ur;
	
	
	@Autowired
	StudentRepository sr;
	
	@Autowired
	CourseRepository cr;
	
	/*@Override
	public List<StudentCourse>findPassed(int passed)
	{
		return studentCourseReopsitory.findByPassed(passed);
	}*/
	
	
	@Override
	public Optional<Student> getCurrentStudent()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<User> user=ur.findByEmail(userDetails.getUsername());
		return sr.findById(user.get(0).getId()) ;

	}
	
	
/*	@Override
	public List<StudentCourse>findPassedCourses(int passed,Student student)
	{
		return studentCourseReopsitory.findByPassedAndStudent(passed, student);
	}
	*/
	@Override
	public List<Course>availableCourses()
	{
	    List<StudentCourse> passedCourses = findPassedCourses2();
	    List<Course> courses=cr.findAll();
	    List<Course> availableCourses=new  ArrayList<Course>();
	    int flag;
	    System.out.println("number of courses"+courses.size());
	    System.out.println("number of passed courses"+passedCourses.size());

	    for(int i=0;i<courses.size();i++)
	    {
	    	flag=0;
	    	for(int j=0;j<passedCourses.size();j++)
	    	{
	    		if(courses.get(i).getId()==passedCourses.get(j).getCourse().getId())
	    		{
	    			flag=1;
	    			break;
	    		}
	    			

	    		
	    	}
	    	if(flag==0)
    		availableCourses.add(courses.get(i));

	    }
	    return availableCourses;
	    
	}
	
	
	@Override
	public List<StudentCourse>registerdCourses()
	{
		return studentCourseReopsitory.findByGradeAndStudent(-1, getCurrentStudent().get());
	}
	

	@Override
	public List<StudentCourse>findPassedCourses2()
	{
		return studentCourseReopsitory.findByGradeGreaterThanEqualAndStudent(50, getCurrentStudent().get());
	}


	@Override
	public List<StudentCourse> findFailedCourses() {
		
		return studentCourseReopsitory.findByGradeGreaterThanEqualAndGradeLessThanEqualAndStudent(0, 49,  getCurrentStudent().get()) ;
	}
	
	
	@Override
	public void deleteRegistered(int courseId)
	{
		Course course=cr.getById(courseId);
		studentCourseReopsitory.delete( studentCourseReopsitory.findByStudentAndCourse(getCurrentStudent().get(),course ));
	}
	
	
	@Override
	public void register(int courseId)
	{
		List<StudentCourse>registerdCourses=registerdCourses();
		if(registerdCourses.size()<6)
		{
			StudentCourse studentCourse=new StudentCourse() ;
			studentCourse.setId(getCurrentStudent().get().getId()*10+courseId);
			studentCourse.setCourse(cr.getById(courseId));
			studentCourse.setStudent(getCurrentStudent().get());
			studentCourseReopsitory.save(studentCourse);
		}
			
	
	}





	
	
	

}
