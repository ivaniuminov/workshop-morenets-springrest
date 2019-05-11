package it.discovery.interceptor;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorisationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AuthorisationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isBlank(request.getHeader(HttpHeaders.AUTHORIZATION)) || !request.getHeader(HttpHeaders.AUTHORIZATION).equals("1234")) {
            log.error("Wrong auth header!");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong auth header!");
            return false;
        }
        return true;
    }
}
