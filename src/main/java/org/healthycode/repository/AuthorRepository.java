package org.healthycode.repository;

import org.healthycode.domain.Author;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AuthorRepository extends GraphRepository<Author>  {
	Author findByUsername(String username);
}
