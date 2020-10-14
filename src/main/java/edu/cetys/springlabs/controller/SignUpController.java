package edu.cetys.springlabs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.cetys.springlabs.enums.UserRole;
import edu.cetys.springlabs.model.UserRegistration;
import edu.cetys.springlabs.service.UserService;


@Controller
public class SignUpController {

	private static Logger logger = LoggerFactory.getLogger("SignUpController.class");
	
	@Autowired
	UserService userService;
	
	@GetMapping("/sign-up")
	public String signUp(Model model) {
	
		List<String> userRoles = new ArrayList<String>();
		
		userRoles.add(UserRole.TOURIST.toString());
		userRoles.add(UserRole.VINTNER.toString());
		
		model.addAttribute("userRoles", userRoles);
		
		return "sign-up";
	}
	
	
	@PostMapping("/sign-up")
	public String loginSubmit(@ModelAttribute UserRegistration userRegistration, /*RedirectAttributes redirectAttributes,*/ Model model) {
		
		logger.info("New user registration");
		//userService.addUser(userRegistration);
		
		//redirectAttributes.addFlashAttribute("confirmationMessage",  "Thank you, you will shortly receive an email with instructions on how to reset your password.");
		
		return "redirect:/";
	}
	
}
