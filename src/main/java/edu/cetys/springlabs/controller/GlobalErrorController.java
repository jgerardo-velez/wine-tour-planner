package edu.cetys.springlabs.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GlobalErrorController implements ErrorController {

	@Override
	@RequestMapping("/error")
    @ResponseBody
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "No Mapping Found";
	}

	
}
