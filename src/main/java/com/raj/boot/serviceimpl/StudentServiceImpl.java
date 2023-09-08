
package com.raj.boot.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.raj.boot.dto.StudentRequest;
import com.raj.boot.dto.StudentResponse;
import com.raj.boot.entity.Student;
import com.raj.boot.exception.StudentNotFoundByEmail;
import com.raj.boot.exception.StudentNotFoundByIdException;
import com.raj.boot.exception.StudentNotFoundByPhNoException;
import com.raj.boot.repository.StudentRepo;
import com.raj.boot.service.StudentService;
import com.raj.boot.util.ResponseStructure;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo repo;

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> saveStudent(StudentRequest studentRequest) {
		Student student = new Student();
		student.setStudentName(studentRequest.getStudentName());
		student.setStudentEmail(studentRequest.getStudentEmail());
		student.setStudentGrade(studentRequest.getStudentGrade());
		student.setStudentPhNo(studentRequest.getStudentPhNo());
		student.setStudentPassword(studentRequest.getStudentPassword());

		Student student2 = repo.save(student);

		StudentResponse response = new StudentResponse();
		response.setStudentId(student2.getStudentId());
		response.setStudentName(student2.getStudentName());
		response.setStudentGrade(student2.getStudentGrade());

		ResponseStructure<StudentResponse> structure = new ResponseStructure<StudentResponse>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Student data saved");
		structure.setData(response);

		return new ResponseEntity<ResponseStructure<StudentResponse>>(structure, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(StudentRequest studentRequest,
			int studentId) {
		Optional<Student> optional = repo.findById(studentId);
		if (optional.isPresent()) {

			Student std = optional.get();

			Student student = new Student();
			student.setStudentId(studentId);
			student.setStudentName(studentRequest.getStudentName());
			student.setStudentEmail(studentRequest.getStudentEmail());
			student.setStudentGrade(studentRequest.getStudentGrade());
			student.setStudentPhNo(studentRequest.getStudentPhNo());

			Student updat = repo.save(student);

			StudentResponse response = new StudentResponse();
			response.setStudentId(studentId);
			response.setStudentName(updat.getStudentName());
			response.setStudentGrade(updat.getStudentGrade());

			ResponseStructure<StudentResponse> structure = new ResponseStructure<StudentResponse>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Student Details Updated");
			structure.setData(response);
			return new ResponseEntity<ResponseStructure<StudentResponse>>(structure, HttpStatus.OK);
		} else {
			throw new StudentNotFoundByIdException("Failed To Update Student!!");
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> deleteStudent(int studentId) {
		Optional<Student> optional = repo.findById(studentId);

		if (optional.isPresent()) {
			Student std = optional.get();
			repo.delete(std);
			StudentResponse response = new StudentResponse();

			response.setStudentId(std.getStudentId());
			response.setStudentName(std.getStudentName());
			response.setStudentGrade(std.getStudentGrade());

			ResponseStructure<StudentResponse> structure = new ResponseStructure<StudentResponse>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Student Details Deleted");
			structure.setData(response);
			return new ResponseEntity<ResponseStructure<StudentResponse>>(structure, HttpStatus.OK);
		} else {
			throw new StudentNotFoundByIdException("Failed to delete the student !!!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> findStudentById(int studentId) {

		Optional<Student> optional = repo.findById(studentId);
		if (optional.isPresent()) {
			Student student = optional.get();

			StudentResponse response = new StudentResponse();
			response.setStudentId(student.getStudentId());
			response.setStudentName(student.getStudentName());
			response.setStudentGrade(student.getStudentGrade());

			ResponseStructure<StudentResponse> structure = new ResponseStructure<StudentResponse>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Student Detail Found");
			structure.setData(response);
			return new ResponseEntity<ResponseStructure<StudentResponse>>(structure, HttpStatus.FOUND);
		}
		throw new StudentNotFoundByIdException("Failed To Get Student Details!!");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StudentResponse>>> findAllStudent() {

		List<Student> list = repo.findAll();
		List<StudentResponse> listResponses = new ArrayList<StudentResponse>();
		
		for(Student std:list)
		{
			StudentResponse response= new StudentResponse();
			response.setStudentId(std.getStudentId());
			response.setStudentName(std.getStudentName());
			response.setStudentGrade(std.getStudentGrade());
			
			listResponses.add(response);
			
		}
		ResponseStructure<List<StudentResponse>> structure = new ResponseStructure<List<StudentResponse>>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Student Details Found!!");
		structure.setData(listResponses);
		return new ResponseEntity<ResponseStructure<List<StudentResponse>>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> findByStudentEmail(String email) {
		Student student = repo.findByStudentEmail(email);

		if (student != null) {
			StudentResponse response = new StudentResponse();
			response.setStudentId(student.getStudentId());
			response.setStudentName(student.getStudentName());
			response.setStudentGrade(student.getStudentGrade());

			ResponseStructure<StudentResponse> structure = new ResponseStructure<StudentResponse>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Student Found  based On Email!!");
			structure.setData(response);
			return new ResponseEntity<ResponseStructure<StudentResponse>>(structure, HttpStatus.FOUND);

		} else {
			throw new StudentNotFoundByEmail("Failed to Find By Email !!");

		}

	}

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> findByStudentPhNo(long phno) {

		Student student = repo.findByStudentPhNo(phno);

		if (student != null) {

			StudentResponse response = new StudentResponse();
			response.setStudentId(student.getStudentId());
			response.setStudentName(student.getStudentName());
			response.setStudentGrade(student.getStudentGrade());

			ResponseStructure<StudentResponse> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Student Found Based On Phono Number");
			structure.setData(response);

			return new ResponseEntity<ResponseStructure<StudentResponse>>(structure, HttpStatus.FOUND);

		} else {

			throw new StudentNotFoundByPhNoException("Failed To Find Student By Contact Number");
		}
	}
	
//	public ResponseEntity<ResponseStructure<List<String>>> getAllEmailsByGrade(String grade){	
//		
//		List<String> emails=repo.getAllEmailsByGrade(grade);
//		
//		ResponseStructure<List<String>> structure=new ResponseStructure<List<String>>();
//		
//		structure.setStatus(HttpStatus.FOUND.value());
//		structure.setMessage("Student email found");
//		structure.setData(emails);
//		
//		return new ResponseEntity<ResponseStructure<List<String>>>(structure,HttpStatus.FOUND);
//		
//	}

	@Override
	public ResponseEntity<ResponseStructure<List<String>>> getAllEmailsByGrade(String grade) {

		List<String> emails=repo.getAllEmailsByGrade(grade);
		
		ResponseStructure<List<String>> structure=new ResponseStructure<List<String>>();
		
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Student email found");
		structure.setData(emails);
		
		return new ResponseEntity<ResponseStructure<List<String>>>(structure,HttpStatus.FOUND);
		
	}

}
