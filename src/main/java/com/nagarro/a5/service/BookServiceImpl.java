package com.nagarro.a5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.a5.dao.BookDao;
import com.nagarro.a5.entity.Book;

@Service
public class BookServiceImpl{

	private BookDao bookDao;

	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Transactional
	public List<Book> findAll() {
		return (List<Book>) bookDao.findAll();
	}

	@Transactional
	public Book findById(int theId) {
		
		Optional<Book> optional = bookDao.findById(theId);
		if(optional.isPresent())
			return optional.get();
		else
			throw new RuntimeException("Book with id " + theId + " not present in DB");
	}

	@Transactional
	public void save(Book book) {
		bookDao.save(book);

	}

	@Transactional
	public void deleteById(int theId) {
		bookDao.deleteById(theId);
	}

}
