package com.nagarro.a5.service;

import java.util.List;

import com.nagarro.a5.entity.Author;

public interface AuthorService {
	
	public List<Author> findAll();

	public Author findById(int theId);

	public void save(Author theAuthor);

	public void deleteById(int theId);

}
