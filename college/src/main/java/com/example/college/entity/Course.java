package com.example.college.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="courses")
public class Course {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="id")
	private int id;
	
	String name;
	
	@Column(name="hall_number")
	int hallNumber;
	
	
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},optional=true)
    @JoinColumn(name = "instructor_id",nullable=true,columnDefinition="integer")
	Instructor instructor;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getHallNumber() {
		return hallNumber;
	}



	public void setHallNumber(int hallNumber) {
		this.hallNumber = hallNumber;
	}



	public Instructor getInstructor() {
		return instructor;
	}



	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
	@OneToMany(mappedBy="course",cascade = CascadeType.ALL)
	List<StudentCourse> studentCourse;



	public List<StudentCourse> getStudentCourse() {
		return studentCourse;
	}



	public void setStudentCourse(List<StudentCourse> studentCourse) {
		this.studentCourse = studentCourse;
	}
	

}
