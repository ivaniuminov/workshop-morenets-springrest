package it.discovery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookInvalidException extends RuntimeException {
    private static final long serialVersionUID = 2312421L;

    public BookInvalidException(int id) {
        super(String.format("Book with id = %s, cannot be rendered!", id));
    }
}
