package it.discovery.controller;

import it.discovery.annotation.JsonPost;
import it.discovery.annotation.JsonPut;
import it.discovery.annotation.JsonXmlGet;
import it.discovery.model.Book;
import it.discovery.model.BookList;
import it.discovery.repository.BookJpaRepository;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookRepository repository;
    @Autowired
    private BookJpaRepository jpaRepository;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @JsonPost
    public void createBook(@RequestBody Book book) {
//        repository.save(book);
        jpaRepository.save(book);
    }

    @JsonXmlGet
    public Book getBook(@PathVariable Integer id) {
//        return repository.findById(id);
        return jpaRepository.findById(id).orElse(null);
    }

    @GetMapping(produces = {APPLICATION_JSON_UTF8_VALUE, APPLICATION_XML_VALUE})
    public BookList getAllBooks() {
        BookList bookList = new BookList();
        //bookList.setBooks(repository.findAll());
        bookList.setBooks(jpaRepository.findAll());

        return bookList;
    }

    @JsonPut
    public void updateBook(@RequestBody Book book, @PathVariable Integer id) {
        book.setId(id);
        //repository.save(book);
        jpaRepository.save(book);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        //repository.delete(id);
        jpaRepository.deleteById(id);
    }
}
