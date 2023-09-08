package com.raj.boot.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.raj.boot.dto.StudentRequest;
import com.raj.boot.dto.StudentResponse;
import com.raj.boot.entity.Student;
import com.raj.boot.util.ResponseStructure;


public interface StudentService {

	public ResponseEntity<ResponseStructure<StudentResponse>> saveStudent(StudentRequest studentRequest);

	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(StudentRequest student, int studentId);

	public ResponseEntity<ResponseStructure<StudentResponse>> deleteStudent(int studentId);

	public ResponseEntity<ResponseStructure<StudentResponse>> findStudentById(int studentId);

	public ResponseEntity<ResponseStructure<List<StudentResponse>>> findAllStudent();

	public ResponseEntity<ResponseStructure<StudentResponse>> findByStudentEmail(String email);

	public ResponseEntity<ResponseStructure<StudentResponse>> findByStudentPhNo(long phno);

	public ResponseEntity<ResponseStructure<List<String>>> getAllEmailsByGrade(String grade);
}
