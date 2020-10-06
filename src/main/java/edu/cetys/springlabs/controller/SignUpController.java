package edu.cetys.springlabs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.cetys.springlabs.enums.UserRole;


@Controller
public class SignUpController {

	@GetMapping("/sign-up")
	public String signUp(Model model) {
	
		List<String> userRoles = new ArrayList<String>();
		
		userRoles.add(UserRole.TOURIST.toString());
		userRoles.add(UserRole.VINTNER.toString());
		
		model.addAttribute("userRoles", userRoles);
		
		return "sign-up";
	}
	
}
