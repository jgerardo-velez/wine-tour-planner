package edu.cetys.springlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cetys.springlabs.model.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

	
}
