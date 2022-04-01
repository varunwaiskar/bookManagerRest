package com.nagarro.a5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.a5.entity.Author;
import com.nagarro.a5.entity.Book;
import com.nagarro.a5.service.AuthorService;
import com.nagarro.a5.service.BookServiceImpl;

@RestController
public class BookController {

	private BookServiceImpl bookService;
	private AuthorService authorService;
	
	@Autowired
	public BookController(BookServiceImpl bookService, AuthorService authorService) {
		this.bookService = bookService;
		this.authorService = authorService;
	}
	
	
	@PostMapping("/book")
	public Book addBook(@RequestBody Book book) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		book.setId(0);
		bookService.save(book);
		
		return book;
	}
	
	
	@PostMapping("/book/{id}")
	public Book addBookByAuthorId(@RequestBody Book book, @PathVariable int id) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		book.setId(0);
		
		Author auth = authorService.findById(id);
		
		book.setAuthor(auth);
		
		bookService.save(book);
		
		return book;
	}
	
	
	@GetMapping("/book")
	public List<Book> findAll() {
		return bookService.findAll();
	}

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable int id) {
		
		Book book = bookService.findById(id);
		
		if (book == null) {
			throw new RuntimeException("Book id not found - " + id);
		}
		
		return book;
	}
	
	
	@PostMapping("/updateBook")
	public Book updateBook(@RequestBody Book book) {
		bookService.save(book);
		return book;
	}
	
	//used if no change is made to author
	@PostMapping("/updateBook/{bookId}")
	public Book updateBook(@RequestBody Book book, @PathVariable int bookId) {
		
		book.setId(bookId);
		bookService.save(book);
		
		return book;
	}

	
	//uused if change to author is made
	@PostMapping("/book/{bookId}/{authorId}")
	public Book updateBookWithAuthor(@RequestBody Book book, @PathVariable int bookId, @PathVariable int authorId) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		book.setId(bookId);
		
		Author auth = authorService.findById(authorId);
		
		book.setAuthor(auth);
		
		bookService.save(book);
		
		return bookService.findById(bookId);
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id) {
		
		try {
			bookService.deleteById(id);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return "Deleted book id - " + id;
	}
	
}
