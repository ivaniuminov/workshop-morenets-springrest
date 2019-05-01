package it.discovery.controller;

import it.discovery.annotation.JsonPost;
import it.discovery.annotation.JsonPut;
import it.discovery.annotation.JsonXmlGet;
import it.discovery.annotation.Logging;
import it.discovery.exception.BookInvalidException;
import it.discovery.model.Book;
import it.discovery.model.BookList;
import it.discovery.repository.BookJpaRepository;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public Book createBook(@Valid @RequestBody Book book) {
//        repository.save(book);
        return jpaRepository.save(book);
    }

    @JsonXmlGet
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
//        return repository.findById(id);
        Optional<Book> book = jpaRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        validateBook(book.get());
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @Logging
    @GetMapping(produces = {APPLICATION_JSON_UTF8_VALUE, APPLICATION_XML_VALUE})
    public BookList getAllBooks() {
        BookList bookList = new BookList();
        //bookList.setBooks(repository.findAll());
        bookList.setBooks(jpaRepository.findAll());

        return bookList;
    }

    @Logging
    @JsonPut
    public Book updateBook(@Valid @RequestBody Book book, @PathVariable Integer id) {
        book.setId(id);
        //repository.save(book);
        return jpaRepository.save(book);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        //repository.delete(id);
        jpaRepository.deleteById(id);
    }

    private void validateBook(Book book) {
        if (book.getId() > 5) {
            throw new NullPointerException();
        }
        if (book.getId() > 3) {
            throw new BookInvalidException(book.getId());
        }
    }
}
