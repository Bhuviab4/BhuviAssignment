package com.raj.boot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StudentRequest {
    @NotNull(message ="Student Name cannot be null")
	private String studentName;
	private String studentEmail;
	@Min(value = 6000000000l, message="Phone Number cannot start below '6' !! " )
	@Max(value = 9999999999l, message="Phone Number cannot be above '9999999999' !!" )
	private long studentPhNo;
	private String studentGrade;
	private String studentPassword;

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

//	public String getStudentPhNo() {
//		return studentPhNo;
//	}
//
//	public void setStudentPhNo(String studentPhNo) {
//		this.studentPhNo = studentPhNo;
//	}
	

	public String getStudentGrade() {
		return studentGrade;
	}

	public long getStudentPhNo() {
		return studentPhNo;
	}

	public void setStudentPhNo(long studentPhNo) {
		this.studentPhNo = studentPhNo;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

}
