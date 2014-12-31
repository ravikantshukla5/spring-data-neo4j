package org.healthycode.service;

import org.healthycode.domain.Article;
import org.healthycode.domain.Author;
import org.healthycode.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Neo4jInItServiceImpl implements Neo4jInItService{
	
	@Autowired
	private AuthorRepository userRepository;

	public void init() {
		if (userRepository.findByUsername("arunram@ramselabs.com") != null) {
			userRepository.delete(userRepository.findByUsername("arunram@ramselabs.com"));
		}
		
		if (userRepository.findByUsername("ravikant.shukla@ramselabs.com") != null) {
			userRepository.delete(userRepository.findByUsername("ravikant.shukla@ramselabs.com"));
		}

		// Create new records
		Author arun = new Author();
		arun.setFirstName("Arun");
		arun.setLastName("Krishnamurthy");
		arun.setPassword("1234");
		arun.setArticle(new Article(1));
		arun.setDesignation("Chief Author");
		arun.setUsername("arunram@ramselabs.com");
		
		Author ravi = new Author();
		ravi.setFirstName("Ravikant");
		ravi.setLastName("Shukla");
		ravi.setPassword("12345");
		ravi.setArticle(new Article(2));
		ravi.setDesignation("Co-Author");
		ravi.setUsername("ravikant.shukla@ramselabs.com");
		
		arun.getArticle().setUser(arun);
		ravi.getArticle().setUser(ravi);

		userRepository.save(arun);
		userRepository.save(ravi);
		
		userRepository.findByUsername("arunram@ramselabs.com").getArticle().getArticle();
	}
}
