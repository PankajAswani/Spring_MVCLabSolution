package com.greatlearning.smsapp_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.smsapp_demo.entity.Student;
import com.greatlearning.smsapp_demo.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> students = studentService.findAll();
		theModel.addAttribute("Students", students);
		return "list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("Student", student);
		return "student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel) {
		Student student = studentService.findById(id);
		theModel.addAttribute("Student", student);
		return "student-form";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		} else {
			student = new Student(firstName, lastName, course, country);
		}
		studentService.save(student);
		return "redirect:/students/list";
	}

	@RequestMapping("/delete")
	public String deleteBook(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/students/list";
	}
}