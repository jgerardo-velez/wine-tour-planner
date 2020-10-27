package edu.cetys.springlabs.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.model.Token;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.repository.TokenRepository;


@Service
public class TokenService {
	
	private static Logger logger = LoggerFactory.getLogger(TokenService.class);
	
	@Autowired
	TokenRepository tokenRepository;
	
	public Token addToken(User user) {
		
		Token token = new Token();
		token.setUserId(user.getId());
		token.setExpirationTime(new Date());
		token.setActive(true);
		
		tokenRepository.save(token);
		
		return token;
	}
}
