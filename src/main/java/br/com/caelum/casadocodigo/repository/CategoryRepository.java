package br.com.caelum.casadocodigo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigo.model.Category;

public interface CategoryRepository extends Repository<Category, Long> {

	
	Category findById(Long id);
	
	Category save(Category category);
	
	
	List<Category> findAll();
}


