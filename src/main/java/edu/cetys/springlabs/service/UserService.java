package edu.cetys.springlabs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.model.UserRegistration;
import edu.cetys.springlabs.repository.UserRepository;


@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public boolean addUser(UserRegistration userRegistration) {
		
		User user = new User();
		
		user.setEmail(userRegistration.getEmail());
		user.setName(userRegistration.getName());
		user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
		user.setActive(Boolean.TRUE);
		
		switch(userRegistration.getRole()) {
			case "Administrator":
				user.setRole("ROLE_ADMIN"); 
			    break;
			case "Vintner":
				user.setRole("ROLE_VINTNER");
			    break;
			default:
				user.setRole("ROLE_TOURIST");
		}
		
		// Save record into database
		User storedUser = userRepository.save(user);
		
		return storedUser != null ? true : false;
	}
	
}
