package it.discovery.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity handleMyException(NullPointerException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                       .body("Bad request, get is forbidden");
    }
}
