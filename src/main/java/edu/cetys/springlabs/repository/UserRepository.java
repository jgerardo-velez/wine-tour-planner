package edu.cetys.springlabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cetys.springlabs.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);


}
