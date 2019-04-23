package it.discovery.repository;

import it.discovery.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookJpaRepository extends JpaRepository<Book, Integer> {
}
