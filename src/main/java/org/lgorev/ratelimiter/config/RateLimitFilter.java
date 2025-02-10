package org.lgorev.ratelimiter.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RateLimitFilter implements WebFilter {
    private final Map<String, RateLimitProperties.RateLimit> rateLimits;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final var key = String.join("-",
                exchange.getRequest().getMethod().name(),
                exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getHeaders().getFirst("x-initiator"));
        final var rateLimit = rateLimits.get(key);
        if (rateLimit != null) {
            log.info("Rate limit for key: {} is {}", key, rateLimit);
        }
        return chain.filter(exchange);
    }
}
