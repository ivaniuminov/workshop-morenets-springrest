package it.discovery.repository;

import java.util.List;

import it.discovery.model.Book;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;

public interface BookRepository {
	Book findById(int id);

	List<Book> findAll();
	
	void save(Book book);
	
	boolean delete(int id);

}
