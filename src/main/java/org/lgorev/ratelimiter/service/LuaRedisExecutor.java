package org.lgorev.ratelimiter.service;

import reactor.core.publisher.Mono;

public interface LuaRedisExecutor {
    Mono<Long> incrementWithTtl(String key, long ttl);
}
