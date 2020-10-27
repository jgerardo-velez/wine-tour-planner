package edu.cetys.springlabs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.cetys.springlabs.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	@Override
	@Query("select u from User u where u.active = true")
	List<User> findAll();

}
