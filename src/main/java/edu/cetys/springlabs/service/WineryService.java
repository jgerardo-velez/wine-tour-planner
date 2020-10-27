package edu.cetys.springlabs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.model.Winery;
import edu.cetys.springlabs.repository.WineryRepository;


@Service
public class WineryService {

private static Logger logger = LoggerFactory.getLogger(WineryService.class);
	
	@Autowired
	WineryRepository wineryRepository;
	
	
	public List<Winery> findAll() {
		
		List<Winery> dbWineries = wineryRepository.findAll();
		
	    return dbWineries;
	}
}
