package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WineryController {

	private static Logger logger = LoggerFactory.getLogger("WineryController.class");
	
	@GetMapping("/winery-dashboard")
	public String wineryDashboard(Model model) {
	
		logger.info("Getting inside the winery dashboard!");
		return "winery-dashboard";
	}
	
}
