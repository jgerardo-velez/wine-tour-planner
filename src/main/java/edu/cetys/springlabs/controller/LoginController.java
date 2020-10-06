package edu.cetys.springlabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	
	@GetMapping("/forgot-password")
	public String forgotPasswordForm(Model model) {
	
		return "forgot-password";
	}
	
}
