package edu.cetys.springlabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignUpController {

	@GetMapping("/sign-up")
	public String signUp(Model model) {
	
		return "sign-up";
	}
	
}
