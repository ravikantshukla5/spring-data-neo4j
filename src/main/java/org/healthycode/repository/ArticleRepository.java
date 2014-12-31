package org.healthycode.repository;

import org.healthycode.domain.Article;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArticleRepository extends GraphRepository<Article>  {
	
}
