package org.healthycode.service;

import org.healthycode.domain.Role;
import org.healthycode.domain.User;
import org.healthycode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service for initializing Neo4J Database with sample data using you can use this service for 
 * initializing the resources
 */
public class Neo4jInItService {
	
	@Autowired
	private UserRepository userRepository;

	public void init() {
		if (userRepository.findByUsername("arunram@ramselabs.com") != null) {
			userRepository.delete(userRepository.findByUsername("arunram@ramselabs.com"));
		}
		
		if (userRepository.findByUsername("ravikant.shukla@ramselabs.com") != null) {
			userRepository.delete(userRepository.findByUsername("ravikant.shukla@ramselabs.com"));
		}

		// Create new records
		User arun = new User();
		arun.setFirstName("Arun");
		arun.setLastName("Krishnamurthy");
		arun.setPassword("1234");
		arun.setRole(new Role(1));
		arun.setUsername("arunram@ramselabs.com");
		
		User ravi = new User();
		ravi.setFirstName("Ravikant");
		ravi.setLastName("Shukla");
		ravi.setPassword("12345");
		ravi.setRole(new Role(2));
		ravi.setUsername("ravikant.shukla@ramselabs.com");
		
		arun.getRole().setUser(arun);
		ravi.getRole().setUser(ravi);

		userRepository.save(arun);
		userRepository.save(ravi);
		
		userRepository.findByUsername("john").getRole().getRole();
	}
}
