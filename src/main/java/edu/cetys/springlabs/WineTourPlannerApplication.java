package edu.cetys.springlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.cetys.springlabs.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class WineTourPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineTourPlannerApplication.class, args);
	}

}
