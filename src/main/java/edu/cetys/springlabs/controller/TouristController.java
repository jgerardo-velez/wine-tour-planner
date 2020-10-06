package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TouristController {

	private static Logger logger = LoggerFactory.getLogger("TouristController.class");
	
	@GetMapping("/tourist-dashboard")
	public String touristDashboard(Model model) {
	
		logger.info("Getting inside the tourist dashboard!");
		return "tourist-dashboard";
	}
	
}
