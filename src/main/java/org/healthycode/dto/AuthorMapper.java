package org.healthycode.dto;

import java.util.ArrayList;
import java.util.List;

import org.healthycode.domain.Author;
import org.healthycode.dto.AuthorDto;

public class AuthorMapper {

	public static AuthorDto map(Author author) {
			AuthorDto dto = new AuthorDto();
			dto.setId(author.getId());
			dto.setFirstName(author.getFirstName());
			dto.setLastName(author.getLastName());
			dto.setUsername(author.getUsername());
			dto.setArticle(author.getArticle().getArticle());
			dto.setDesignation(author.getDesignation());
			return dto;
	}
	
	public static List<AuthorDto> map(List<Author> authors) {
		List<AuthorDto> dtos = new ArrayList<AuthorDto>();
		for (Author author: authors) {
			dtos.add(map(author));
		}
		return dtos;
	}
}
