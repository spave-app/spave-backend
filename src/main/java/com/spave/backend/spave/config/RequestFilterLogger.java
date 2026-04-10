package com.spave.backend.spave.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class RequestFilterLogger extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.nanoTime();
        String requestId = request.getRequestId();
        MDC.put("requestID", requestId);

        try {
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Exception occurred during request processing: {}", e.getMessage());
            throw e;
        } finally {
            long end = System.nanoTime();
            long durationInMillisecond = (end - start) / 1_000_000;
            log.info("[{}] {} {} -> {} | {}ms",
                requestId,
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                durationInMillisecond
            );
            MDC.clear();
        }
    }
}
