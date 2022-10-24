package com.example.college.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.college.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
