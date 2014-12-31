package org.healthycode.domain;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Author {
	
	@GraphId
	private Long id;
	
	private String firstName;
	private String lastName;
	private String designation;
	
	@Indexed
	private String username;
	private String password;
	
	@Fetch @RelatedTo(type = "HAS_WRITTEN")
	private Article article;
	
	public Author() {}
	
	public Author(String username, String password, String firstName, String lastName, Article article, String designation) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.article = article;
		this.designation = designation;
	}
	
	public Author(String username, String firstName, String lastName, Article article,String designation) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.article = article;
		this.designation = designation;
	}

	public Author(String username) {
		this.username = username;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
