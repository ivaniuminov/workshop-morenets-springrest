package it.discovery.monitoring;

import it.discovery.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AvailableBookHealthIndicator implements HealthIndicator {

    @Autowired
    private BookJpaRepository repository;

    @Override
    public Health health() {
        if (repository.findAll().isEmpty()) {
            return Health.down().withDetail("Message", "No books are present").build();
        }

        return Health.up().build();
    }
}
