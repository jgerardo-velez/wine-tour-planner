package edu.cetys.springlabs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.model.Product;
import edu.cetys.springlabs.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	
	public List<Product> findAllByWinery(int wineryId) {
		
		List<Product> dbProducts = productRepository.findAllByWinery(wineryId);
		
	    return dbProducts;
	}
	
}
