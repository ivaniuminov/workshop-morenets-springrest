package it.discovery.config;

import it.discovery.interceptor.AuthorisationInterceptor;
import it.discovery.interceptor.HeaderInterceptor;
import it.discovery.interceptor.LoggingInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorisationInterceptor());
        registry.addInterceptor(new LoggingInterceptor());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(10))
                       .setReadTimeout(Duration.ofSeconds(10))
                       .uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"))
                       .additionalInterceptors(new HeaderInterceptor())
                       .errorHandler(new RestTemplateErrorHandler())
                       .build();
    }
}
