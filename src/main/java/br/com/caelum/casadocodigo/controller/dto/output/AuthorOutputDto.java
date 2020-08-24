package br.com.caelum.casadocodigo.controller.dto.output;

import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigo.model.Author;


public class AuthorOutputDto {
	
	private Long id;
	private String name;
    private String email;
    private String avatarUrl;
    private String description;
	
    public AuthorOutputDto(Author author) {
		this.id = author.getId();
		this.name = author.getName();
		this.email = author.getEmail();
		this.avatarUrl = author.getAvatarUrl();
		this.description = author.getDescription();
		
    }
    
    
    public static List<AuthorOutputDto> listFromAuthors(List<Author> authors) {
		return authors.stream()
				.map(AuthorOutputDto::new)
				.collect(Collectors.toList());
	}
    
    public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public String getDescription() {
		return description;
	}
    
    

}
