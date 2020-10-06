package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger("AdminController.class");
	
	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model) {
	
		logger.info("Getting inside the winery dashboard!");
		return "admin-dashboard";
	}
	
}
