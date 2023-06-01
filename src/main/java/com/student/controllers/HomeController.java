package com.student.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/public")
	public String home() {
		
		System.out.println("i am suraj");
		
		System.out.println("i am running");
		return "public";
	}

	@PreAuthorize("hasRole('normal')")
	@GetMapping("/normal")
	public String normal() {
		return "normal";
	}

	@PreAuthorize("hasRole('admin')")
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

}
