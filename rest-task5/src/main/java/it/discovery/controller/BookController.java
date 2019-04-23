package it.discovery.controller;

import it.discovery.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("book")
public class BookController {	

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book getBook() {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("Martin Fawler");
        book.setName("Effective Java");
        book.setYear(2018);

        return book;
    }
}
