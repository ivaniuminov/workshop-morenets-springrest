package it.discovery.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add(HttpHeaders.ACCEPT, APPLICATION_JSON_UTF8_VALUE);
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "1234");
        request.getHeaders().add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
        return execution.execute(request, body);
    }
}
