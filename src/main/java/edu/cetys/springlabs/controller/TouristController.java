package edu.cetys.springlabs.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TouristController {

	private static Logger logger = LoggerFactory.getLogger("TouristController.class");
	
	@GetMapping("/tourist-dashboard")
	public String touristDashboard(HttpServletRequest request, Model model) {
	
		logger.info("Getting inside the tourist dashboard!");
		
		logger.info("AdminController -> User: " + request.getUserPrincipal().getName());
		
		return "tourist-dashboard";
	}
	
}
