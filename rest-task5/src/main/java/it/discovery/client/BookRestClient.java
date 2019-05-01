package it.discovery.client;

import it.discovery.config.RestTemplateErrorHandler;
import it.discovery.interceptor.HeaderInterceptor;
import it.discovery.model.Book;
import it.discovery.model.BookList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

public class BookRestClient {

    private final RestTemplate restTemplate;

    {
        restTemplate = new RestTemplateBuilder()
                               .setConnectTimeout(Duration.ofSeconds(10))
                               .setReadTimeout(Duration.ofSeconds(10))
                               .uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"))
                               .additionalInterceptors(new HeaderInterceptor())
                               .messageConverters(new MappingJackson2HttpMessageConverter())
                               .errorHandler(new RestTemplateErrorHandler())
                               .build();
    }

    private ResponseEntity<Book> createBook(Book book) {
        return restTemplate.postForEntity("/book", book, Book.class);
    }

    private ResponseEntity<Book> findById(int id) {
        return restTemplate.getForEntity("/book/" + id, Book.class);
    }

    private ResponseEntity<BookList> findAll() {
        return restTemplate.exchange("/book", HttpMethod.GET, null, BookList.class);
    }

    private ResponseEntity<Book> updateById(Book book, int id) {
        return restTemplate.exchange("/book/" + id, HttpMethod.PUT, new HttpEntity<>(book), Book.class);
    }

    private void deleteById(int id) {
        restTemplate.delete("/book/" + id, HttpMethod.GET, null, BookList.class);
    }


    public static void main(String[] args) {
        Book book = new Book();
        book.setAuthor("Arthur");
        book.setYear(1111);
        book.setName("Sherlok");

        BookRestClient client = new BookRestClient();
        System.out.println(client.createBook(book).getBody());
        System.out.println(client.createBook(book).getBody());
        System.out.println(client.findById(1).getBody());
        System.out.println(client.findAll().getBody());

        book.setName("Sherlock Holmes");
        book.setYear(2020);
        System.out.println(client.updateById(book, 1).getBody());
        client.deleteById(1);
    }
}
