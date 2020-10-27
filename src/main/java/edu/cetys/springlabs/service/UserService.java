package edu.cetys.springlabs.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.dto.form.UserRegistrationForm;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.repository.UserRepository;


@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> findAll() {
		
		List<User> dbUsers = userRepository.findAll();
		
	    return dbUsers;
	}
	
	public User findByEmail(String email) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + email));
		
		User dbUser = optionalUser.get(); 
		
		
		return dbUser;
	}
	
	public boolean addUser(UserRegistrationForm userRegistration) {
		
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
		int storedUser = userRepository.save(user);
		
		return storedUser == 1 ? true : false;
	}
	
}
