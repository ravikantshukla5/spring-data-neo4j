package org.healthycode.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Article {
	
	@GraphId
	private Long id;
	private Author user;
	private Integer article;
	
	public Article() {}
	
	public Article(Integer article) {
		this.article = article;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Author getUser() {
		return user;
	}
	public void setUser(Author user) {
		this.user = user;
	}

	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}
}
