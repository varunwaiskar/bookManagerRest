package com.nagarro.a5.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.a5.entity.Author;

@Repository
public interface AuthorDao extends CrudRepository<Author, Integer> {}
