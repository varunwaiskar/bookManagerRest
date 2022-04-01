package com.nagarro.a5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.a5.entity.Author;
import com.nagarro.a5.service.AuthorService;

@RestController
public class AuthorRestController {

	private AuthorService authorService;

	@Autowired
	public AuthorRestController(AuthorService theAuthorService) {
		authorService = theAuthorService;
	}
	
	
	@PostMapping("/author")
	public Author addAuthor(@RequestBody Author author) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		author.setId(0);
		
		authorService.save(author);
		
		return author;
	}
	
	
	@GetMapping("/author")
	public List<Author> findAll() {
		return authorService.findAll();
	}

	@GetMapping("/author/{id}")
	public Author getAuthor(@PathVariable int id) {
		
		Author author = authorService.findById(id);
		
		if (author == null) {
			throw new RuntimeException("Author id not found - " + id);
		}
		
		return author;
	}
	
	@PostMapping("/updateAuthor/{id}")
	public Author updateAuthor(@RequestBody Author author, @PathVariable int id) {
		
		author.setId(id);
		authorService.save(author);
		
		return author;
	}

	@GetMapping("/deleteAuthor/{id}")
	public String deleteAuthor(@PathVariable int id) {
		
		try {
			authorService.deleteById(id);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return "Deleted employee id - " + id;
	}
	
}
