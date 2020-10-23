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

import edu.cetys.springlabs.dto.WineryDto;
import edu.cetys.springlabs.model.Winery;
import edu.cetys.springlabs.service.WineryService;

@Controller
public class TouristController {

	private static Logger logger = LoggerFactory.getLogger("TouristController.class");
	
	@Autowired
	WineryService wineryService;
	
	@GetMapping("/tourist-dashboard")
	public String touristDashboard(HttpServletRequest request, Model model) {
	
		logger.info("Getting inside the tourist dashboard!");
		
		logger.info("AdminController -> User: " + request.getUserPrincipal().getName());
		
		List<Winery> dbWineries = wineryService.findAll();
		
		List<WineryDto> wineries = new ArrayList<WineryDto>();
		
		for (Winery dbWinery : dbWineries) {
			WineryDto wineryDto = new WineryDto();
			
			wineryDto.setId(dbWinery.getId());
			wineryDto.setName(dbWinery.getName());
			wineryDto.setAddress(dbWinery.getAddress());
			wineryDto.setPhone(dbWinery.getPhone());
			wineryDto.setWebsite(dbWinery.getWebsite());
			
			wineries.add(wineryDto);
		}
		
		model.addAttribute("wineries", wineries);
		
		return "tourist-dashboard";
	}
	
}
