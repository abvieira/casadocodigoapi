package br.com.caelum.casadocodigo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.casadocodigo.controller.dto.input.NewAuthorInputDto;
import br.com.caelum.casadocodigo.controller.dto.input.NewCategoryInputDto;
import br.com.caelum.casadocodigo.controller.dto.output.AuthorOutputDto;
import br.com.caelum.casadocodigo.controller.dto.output.CategoryOutputDto;
import br.com.caelum.casadocodigo.model.Author;
import br.com.caelum.casadocodigo.model.Category;
import br.com.caelum.casadocodigo.repository.AuthorRepository;
import br.com.caelum.casadocodigo.repository.CategoryRepository;

@RestController
@RequestMapping("/api/admin/authors")
public class AuthorController {
	
	@Autowired 
	private AuthorRepository authorRepository;


	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AuthorOutputDto> listAuthors() {

	    List<Author> listAuthors = this.authorRepository.findAll();
	    
	    return AuthorOutputDto.listFromAuthors(listAuthors);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorOutputDto> createAuthor(@RequestBody @Valid NewAuthorInputDto newAuthorDto,
		   UriComponentsBuilder uriBuilder) {

	    Author author= newAuthorDto.build();
	    this.authorRepository.save(author);

	    URI path = uriBuilder.path("/api/admin/authors/{id}")
	            .buildAndExpand(author.getId()).toUri();

	    return ResponseEntity.created(path).body(new AuthorOutputDto(author));
	}

	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthorOutputDto foundCategorybyId(@PathVariable Long id){

	    Author showAuthor = this.authorRepository.findById(id);
	    
	    return new AuthorOutputDto(showAuthor);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthorOutputDto updateAuthor(@RequestBody @Valid NewAuthorInputDto newAuthorDto) {

	    Author author = newAuthorDto.build();
	    this.authorRepository.save(author);

	    
	    return (new AuthorOutputDto(author));
	}

}
