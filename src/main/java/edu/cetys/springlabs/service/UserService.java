package edu.cetys.springlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.model.UserCredential;
import edu.cetys.springlabs.model.UserRegistration;
import edu.cetys.springlabs.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public boolean addUser(UserRegistration userRegistration) {
		
		UserCredential userCredential = new UserCredential();
		userCredential.setEmail(userRegistration.getEmail());
		userCredential.setPassword(userRegistration.getPassword());
		userCredential.setRole(userRegistration.getRole());
		
		return userRepository.addUser(userCredential);
	}

	public UserCredential getUser(String email) {
		
		return userRepository.getUser(email);
	}
	
}
