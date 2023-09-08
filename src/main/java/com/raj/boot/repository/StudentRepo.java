package com.raj.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raj.boot.entity.Student;

@Repository
public interface StudentRepo  extends JpaRepository<Student, Integer>{

	public Student findByStudentEmail(String email);
	
	public Student findByStudentPhNo(long phno);
	
	@Query("Select s.studentEmail from Student s where s.studentGrade=?1")
	public List<String> getAllEmailsByGrade(String grade);

	
}
