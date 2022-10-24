package com.example.college.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="students")
public class Student {
	

	@Id
	@Column(name="id")
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getCreiditHours() {
		return creiditHours;
	}

	public void setCreiditHours(int creiditHours) {
		this.creiditHours = creiditHours;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public User getUser() {
		return userS;
	}

	public void setUser(User user) {
		this.userS = user;
	}

	@Column(name="credit_hours")
	private int creiditHours;
	
	@Column(name="gpa")
	private float gpa;

	    @OneToOne()
	    @JoinColumn(name = "id")
	    @MapsId
	    public User userS;
	    
	    
	    @OneToMany(mappedBy="student",cascade = CascadeType.ALL)
		List<StudentCourse> studentCourse;
	    
	    
		public void add(StudentCourse tempCourse)
		{
			if(studentCourse==null)
				studentCourse=new ArrayList<>();
			studentCourse.add(tempCourse);
			
			
		}

		public List<StudentCourse> getStudentCourse() {
			return studentCourse;
		}

		public void setStudentCourse(List<StudentCourse> studentCourse) {
			this.studentCourse = studentCourse;
		}

}
