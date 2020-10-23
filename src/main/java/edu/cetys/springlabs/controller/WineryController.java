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

import edu.cetys.springlabs.dto.ProductDto;
import edu.cetys.springlabs.dto.RegionDto;
import edu.cetys.springlabs.dto.WineryDto;
import edu.cetys.springlabs.model.MyUserDetails;
import edu.cetys.springlabs.model.Product;
import edu.cetys.springlabs.model.Region;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.model.Winery;
import edu.cetys.springlabs.service.UserService;

@Controller
public class WineryController {

	private static Logger logger = LoggerFactory.getLogger("WineryController.class");
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/winery-dashboard")
	public String wineryDashboard(HttpServletRequest request, Model model) {
	
		/*
		logger.info("Getting inside the winery dashboard!");
		logger.info("AdminController -> User: " + request.getUserPrincipal());
		logger.info("AdminController -> User: " + request.getUserPrincipal().getName());
		*/
		
		// TODO: Try to include the user id in MyUserDetails model
		User dbUser = userService.findByEmail(request.getUserPrincipal().getName());
		
		/*
		logger.info("===== DB USER =====");
		logger.info("dbUser.Id: " + dbUser.getId());
		logger.info("dbUser.Email: " + dbUser.getEmail());
		*/
		
		Winery dbWinery = dbUser.getWinery();
		
		if (dbWinery != null) {
			// convert to dto objects
			WineryDto wineryDto = new WineryDto();
			wineryDto.setId(dbWinery.getId());
			wineryDto.setName(dbWinery.getName());
			wineryDto.setAddress(dbWinery.getAddress());
			wineryDto.setPhone(dbWinery.getPhone());
			wineryDto.setWebsite(dbWinery.getWebsite());
			
			Region region = dbWinery.getRegion();
			
			if (region != null) {
				RegionDto regionDto = new RegionDto();
				regionDto.setId(region.getId());
				regionDto.setName(region.getName());
				regionDto.setCode(region.getCode());
				regionDto.setCountry(region.getCountry());
				
				wineryDto.setRegion(regionDto);
			}
			
			List<Product> dbProducts = dbWinery.getProducts();
			List<ProductDto> productsDtos = new ArrayList<ProductDto>();
			
			if (dbProducts != null) {
				
				for (Product dbProduct : dbProducts) {
					ProductDto productDto = new ProductDto();
					productDto.setSku(dbProduct.getSku());
					productDto.setName(dbProduct.getName());
					productDto.setPrice(dbProduct.getPrice());
					productDto.setCurrency(dbProduct.getCurrency());
					productDto.setImage(dbProduct.getImage());
					
					productsDtos.add(productDto);
				}
				
				wineryDto.setProducts(productsDtos);
			}
		
			model.addAttribute("winery", wineryDto);
		}
		
		return "winery-dashboard";
	}
	
}
