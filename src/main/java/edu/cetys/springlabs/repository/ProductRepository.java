package edu.cetys.springlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cetys.springlabs.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
