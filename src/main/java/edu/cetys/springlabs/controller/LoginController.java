package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.cetys.springlabs.model.UserCredential;


@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger("LoginController.class");
	
	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute UserCredential userCredential, Model model) {
		
		logger.info("Email: " + userCredential.getEmail());
		logger.info("Password: " + userCredential.getPassword());
		
		if ( !userCredential.getPassword().equals("123") ) {
			return "home";
		}
		
		String dashboard = null;
		
		switch(userCredential.getEmail()) {
		  case "admin@cetys.mx":
		    dashboard = "admin-dashboard";
		    break;
		  case "tourist@cetys.mx":
			dashboard = "tourist-dashboard";
		    break;
		  case "winery@cetys.mx":
				dashboard = "winery-dashboard";
			    break;  
		  default:
			  dashboard = "home";
		}
			  
		return "redirect:" + dashboard;
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
	
		return "redirect:/";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPasswordForm(Model model) {
	
		return "forgot-password";
	}
	
}
