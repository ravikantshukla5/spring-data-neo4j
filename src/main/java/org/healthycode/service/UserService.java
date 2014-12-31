package org.healthycode.service;

import java.util.ArrayList;
import java.util.List;

import org.healthycode.domain.User;
import org.healthycode.repository.RoleRepository;
import org.healthycode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly=false)
	public User create(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser != null) {
			throw new RuntimeException("Record already exists!");
		}
		
		user.getRole().setUser(user);
		return userRepository.save(user);
	}
	
	public User read(User user) {
		return user;
	}
	
	public List<User> readAll() {
		List<User> users = new ArrayList<User>();
		
		Result<User> results = userRepository.findAll();
		for (User r: results) {
			users.add(r);
		}
		
		return users;
	}
	@Transactional(readOnly=false)
	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return null;
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());
		
		roleRepository.save(existingUser.getRole());
		return userRepository.save(existingUser);
	}
	@Transactional(readOnly=false)
	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return false;
		}
		
		userRepository.delete(existingUser);
		return true;
	}
}
