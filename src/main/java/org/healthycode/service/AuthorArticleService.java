package org.healthycode.service;

import java.util.List;

import org.healthycode.domain.Article;
import org.healthycode.domain.Author;
import org.healthycode.domain.AuthorArticleRelationship;
/**
 * @author Ravikant
 * This service is used to perform CRUD operations for {@link Author} and {@link Article} repositories
 *
 */
public interface AuthorArticleService {
	/**
	 * This method is used to create an {@link Article} and {@link Author} nodes and attached them via {@link AuthorArticleRelationship}
	 * @param user
	 * @return {@link Author}
	 */
	Author create(Author user);
	 /**
	  * This method is used to read author for modification based on username of author
	 * @param user
	 * @return {@link Author}
	 */
	Author read(Author user);
	 /**
	  * This method is used to to read all authors that are there in data base
	 * @return List of {@link Author}
	 */
	List<Author> readAll();
	 /**
	  * This method is used to update the {@link Author} based on selected properties passed
	 * @param user
	 * @return updated {@link Author}
	 */
	Author update(Author user);
	 /**
	  * This method is used to delete the {@link Author} based on selected properties passed
	 * @param user
	 * @return true if deleted false if not
	 */
	Boolean delete(Author user);

}
