package edu.cetys.springlabs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.cetys.springlabs.dto.UserDto;
import edu.cetys.springlabs.enums.UserRole;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.service.UserService;

@Controller
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger("AdminController.class");
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/admin-dashboard")
	public String adminDashboard(HttpServletRequest request, Model model) {
	
		logger.info("Getting inside the winery dashboard!");
		
		logger.info("AdminController -> User: " + request.getUserPrincipal().getName());
		
		List<User> dbUsers = userService.findAll();
		
		List<UserDto> users = new ArrayList<UserDto>();
		
		for (User dbUser : dbUsers) {
			UserDto userDto = new UserDto();
			userDto.setId(dbUser.getId());
			userDto.setEmail(dbUser.getEmail());
			userDto.setName(dbUser.getName());
			userDto.setActive(dbUser.isActive());
			
			if (dbUser.getRole().contains("ROLE_ADMIN")) {
				userDto.setRole(UserRole.ADMIN.toString());
	        } else if (dbUser.getRole().contains("ROLE_VINTNER")) {
	        	userDto.setRole(UserRole.VINTNER.toString());
	        } else {
	        	userDto.setRole(UserRole.TOURIST.toString());
	        }
			
			users.add(userDto);
		}
		
		model.addAttribute("users", users);
		
		return "admin-dashboard";
	}
	
}
