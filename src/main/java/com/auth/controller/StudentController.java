package com.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
private	List<Student>stnlist=new ArrayList<>(List.of(
		new Student(1,"mithilesh","79"),
		new Student(2,"Ganesh","99")
		));
			
			
	
@GetMapping("/")
 List<Student>studentlist(){
	return stnlist;
}
@PostMapping("/student")
public Student addstudent(@RequestBody Student students) {
	stnlist.add(students);
	return students;
}
// this is for generate token
@GetMapping("/csrf-token")
public CsrfToken crsCsrfToken(HttpServletRequest request) {
	return (CsrfToken) request.getAttribute("_csrf");
	
}
}
