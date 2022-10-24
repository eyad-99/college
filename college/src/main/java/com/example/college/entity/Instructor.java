package com.example.college.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name =  "instructors")
public class Instructor {
	
	
	@Id
	@Column(name="id")
	private int id;
	

	
	
	    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public User getUser() {
		return userI;
	}


	public void setUser(User user) {
		this.userI = user;
	}


		@OneToOne()
	    @JoinColumn(name = "id")
	    @MapsId
	    private User userI;
		
		
		public List<Course> getCourses() {
			return courses;
		}


		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}


		@OneToMany(fetch = FetchType.EAGER,mappedBy = "instructor",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
		List<Course> courses;
		
		//
		public void add(Course tempCourse)
		{
			if(courses==null)
				courses=new ArrayList<>();
			courses.add(tempCourse);
			tempCourse.setInstructor(this);
			
		}
		
		
		public void delete(Course tempCourse)
		{
		
			courses.remove(tempCourse);
		}

}
