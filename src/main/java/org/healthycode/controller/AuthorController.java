package org.healthycode.controller;

import org.healthycode.domain.Article;
import org.healthycode.domain.Author;
import org.healthycode.dto.AuthorDto;
import org.healthycode.dto.AuthorListDto;
import org.healthycode.dto.AuthorMapper;
import org.healthycode.service.AuthorArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorArticleService service;
	
	@RequestMapping
	public String getUsersPage() {
		return "authors";
	}
	
	@RequestMapping(value="/records")
	public @ResponseBody AuthorListDto getUsers() {
		
		AuthorListDto authorListDto = new AuthorListDto();
		authorListDto.setAuthors(AuthorMapper.map(service.readAll()));
		return authorListDto;
	}
	
	@RequestMapping(value="/get")
	public @ResponseBody Author get(@RequestBody Author user) {
		return service.read(user);
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody AuthorDto create(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String designation,
			@RequestParam Integer article) {

		Article newArticle = new Article();
		newArticle.setArticle(article);
		
		Author newAuthor = new Author();
		newAuthor.setUsername(username);
		newAuthor.setPassword(password);
		newAuthor.setFirstName(firstName);
		newAuthor.setLastName(lastName);
		newAuthor.setArticle(newArticle);
		newAuthor.setDesignation(designation);
		
		return AuthorMapper.map(service.create(newAuthor));
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody AuthorDto update(
			@RequestParam String username,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String designation,
			@RequestParam Integer article) {

		Article existingArticle = new Article();
		existingArticle.setArticle(article);
		
		Author existingAuthor = new Author();
		existingAuthor.setUsername(username);
		existingAuthor.setFirstName(firstName);
		existingAuthor.setLastName(lastName);
		existingAuthor.setArticle(existingArticle);
		existingAuthor.setDesignation(designation);
		return AuthorMapper.map(service.update(existingAuthor));
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody Boolean delete(
			@RequestParam String username) {

		Author existingUser = new Author();
		existingUser.setUsername(username);
		
		return service.delete(existingUser);
	}
}
