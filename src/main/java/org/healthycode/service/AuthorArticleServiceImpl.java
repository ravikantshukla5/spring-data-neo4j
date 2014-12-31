package org.healthycode.service;

import java.util.ArrayList;
import java.util.List;

import org.healthycode.domain.Author;
import org.healthycode.repository.RoleRepository;
import org.healthycode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class AuthorArticleServiceImpl implements AuthorArticleService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly=false)
	public Author create(Author user) {
		Author existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser != null) {
			throw new RuntimeException("Record already exists!");
		}
		
		user.getArticle().setUser(user);
		return userRepository.save(user);
	}
	
	public Author read(Author user) {
		return user;
	}
	
	public List<Author> readAll() {
		List<Author> users = new ArrayList<Author>();
		
		Result<Author> results = userRepository.findAll();
		for (Author r: results) {
			users.add(r);
		}
		
		return users;
	}
	@Transactional(readOnly=false)
	public Author update(Author user) {
		Author existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return null;
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getArticle().setArticle(user.getArticle().getArticle());
		
		roleRepository.save(existingUser.getArticle());
		return userRepository.save(existingUser);
	}
	@Transactional(readOnly=false)
	public Boolean delete(Author user) {
		Author existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return false;
		}
		
		userRepository.delete(existingUser);
		return true;
	}
}
