package com.nagarro.a5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.a5.dao.AuthorDao;
import com.nagarro.a5.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorDao authorDao;

	@Autowired
	public AuthorServiceImpl(AuthorDao theAuthorDao) {
		authorDao = theAuthorDao;
	}

	@Override @Transactional
	public List<Author> findAll() {
		return (List<Author>) authorDao.findAll();
	}

	@Override @Transactional
	public Author findById(int theId) {
		
		Optional<Author> optional = authorDao.findById(theId);
		if(optional.isPresent())
			return optional.get();
		else
			throw new RuntimeException("Author with id " + theId + " not present in DB");
	}

	@Override @Transactional
	public void save(Author theAuthor) {
		authorDao.save(theAuthor);

	}

	@Override @Transactional
	public void deleteById(int theId) {
		authorDao.deleteById(theId);
	}

}
