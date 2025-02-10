package org.lgorev.ratelimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class RateLimitConfig {
    @Bean
    public Map<String, RateLimitProperties.RateLimit> rateLimits(RateLimitProperties rateLimitProperties) {
        return rateLimitProperties.getRateLimits().stream()
                .collect(Collectors.toMap(
                        it -> String.join("-", it.method(), it.url(), it.initiator()),
                        Function.identity()));
    }
}
