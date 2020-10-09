package edu.cetys.springlabs.repository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import edu.cetys.springlabs.enums.UserRole;
import edu.cetys.springlabs.model.UserCredential;


@Repository
public class UserRepository {

	private static Logger logger = LoggerFactory.getLogger("UserRepository.class");
	
	HashMap<String, UserCredential> usersInMemory = new HashMap<String, UserCredential>();
	
	
	public UserRepository() {
		
		usersInMemory.put("admin@winetourplanner.com", 
				new UserCredential("admin@winetourplanner.com", "cetys123", UserRole.ADMIN.toString()));
		
		usersInMemory.put("tourist@winetourplanner.com", 
				new UserCredential("tourist@winetourplanner.com", "cetys123", UserRole.TOURIST.toString()));
		
		usersInMemory.put("vintner@winetourplanner.com", 
				new UserCredential("vintner@winetourplanner.com", "cetys123", UserRole.VINTNER.toString()));
	}
	
	
	public boolean addUser(UserCredential userCredential) {
		
		usersInMemory.put(userCredential.getEmail(), userCredential);
		
		logger.info("Adding a new User: " + userCredential.toString());
		
		return true;
	}
	
	
	public UserCredential getUser(String email) {
		
		return usersInMemory.get(email);
	}
	
}
