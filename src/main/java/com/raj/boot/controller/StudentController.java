package com.raj.boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raj.boot.dto.StudentRequest;
import com.raj.boot.dto.StudentResponse;
import com.raj.boot.entity.Student;
import com.raj.boot.service.StudentService;
import com.raj.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController//@ResponseBody+@controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<StudentResponse>> saveStudent(@RequestBody @Valid StudentRequest studentRequest) {

		return service.saveStudent(studentRequest);
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable int studentId) {
		return service.updateStudent(studentRequest, studentId);

	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> deleteStudent(@PathVariable int studentId) {
		return service.deleteStudent(studentId);
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> findStudentById(@PathVariable int studentId) {
		return service.findStudentById(studentId);

	}

//	@CrossOrigin
	@GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<StudentResponse>>> findAllStudent() {
		return service.findAllStudent();
	}
	
	@GetMapping(params="email")
	public ResponseEntity<ResponseStructure<StudentResponse>> findByEmail(@RequestParam String email)
	{
		return service.findByStudentEmail(email);
	}
	
	
	@GetMapping(params ="phno")
	public ResponseEntity<ResponseStructure<StudentResponse>> findByPhNo(@RequestParam long phno)
	{
		return service.findByStudentPhNo(phno);
	}
	
	@GetMapping(params = "grade")
	public ResponseEntity<ResponseStructure<List<String>>> getAllEmailsByGrade(@RequestParam String grade){
		return service.getAllEmailsByGrade(grade);
	}

}
