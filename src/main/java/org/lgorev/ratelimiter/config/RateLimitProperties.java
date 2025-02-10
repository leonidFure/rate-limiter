package org.lgorev.ratelimiter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
public class RateLimitProperties {
    private List<RateLimit> rateLimits;

    public record RateLimit(String url,
                            String method,
                            String initiator,
                            int limit) {
    }
}
