package edu.cetys.springlabs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.cetys.springlabs.model.MyUserDetails;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByEmail(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
		
		return user.map(MyUserDetails::new).get();
	}
	

}
