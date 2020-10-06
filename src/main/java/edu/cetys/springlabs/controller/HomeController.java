package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	private static Logger logger = LoggerFactory.getLogger("HomeController.class");
	
	@Value("${spring.application.name}")
	String appName;
	
	@GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
	
	@GetMapping("/sign-up")
	public String signUp(Model model) {
	
		return "sign-up";
	}
	
	@GetMapping("/winery-dashboard")
	public String wineryDashboard(Model model) {
	
		logger.info("Getting inside the winery dashboard!");
		return "winery-dashboard";
	}
	
	@GetMapping("/tourist-dashboard")
	public String touristDashboard(Model model) {
	
		logger.info("Getting inside the winery dashboard!");
		return "tourist-dashboard";
	}
	
	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model) {
	
		logger.info("Getting inside the winery dashboard!");
		return "admin-dashboard";
	}
	
}
