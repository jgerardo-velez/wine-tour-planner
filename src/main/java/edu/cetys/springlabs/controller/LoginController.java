package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.cetys.springlabs.model.UserCredential;


@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger("LoginController.class");
	
	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute UserCredential userCredential, RedirectAttributes redirectAttributes, Model model) {
		
		logger.info("Email: " + userCredential.getEmail());
		logger.info("Password: " + userCredential.getPassword());
		
		if ( !userCredential.getPassword().equals("cetys123") ) {
			redirectAttributes.addFlashAttribute("errorMessage",  "Invalid Credentials!");
			return "redirect:/";
		}
		
		String dashboard = null;
		
		switch(userCredential.getEmail()) {
		  case "admin@winetourplanner.com":
		    dashboard = "admin-dashboard";
		    break;
		  case "tourist@winetourplanner.com":
			dashboard = "tourist-dashboard";
		    break;
		  case "winery@winetourplanner.com":
				dashboard = "winery-dashboard";
			    break;  
		  default:
			  dashboard = "home";
		}
			  
		return "redirect:/" + dashboard;
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
	
		return "redirect:/";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPasswordForm(Model model) {
	
		
		return "forgot-password";
	}
	
	@PostMapping("/password-reset")
	public String passwordReset(@ModelAttribute UserCredential userCredential, RedirectAttributes redirectAttributes, Model model) {
		
		logger.info("Email: " + userCredential.getEmail());
		
		redirectAttributes.addFlashAttribute("confirmationMessage",  "Thank you, you will shortly receive an email with instructions on how to reset your password.");
		
		return "redirect:/forgot-password";
	}
	
}
