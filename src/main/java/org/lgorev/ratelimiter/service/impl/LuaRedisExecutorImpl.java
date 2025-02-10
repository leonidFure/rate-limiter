package org.lgorev.ratelimiter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lgorev.ratelimiter.service.LuaRedisExecutor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LuaRedisExecutorImpl implements LuaRedisExecutor {
    private static final String SCRIPT = """
                local exists = redis.call('EXISTS', KEYS[1])
                local newValue = redis.call('INCR', KEYS[1])
                if exists == 0 then
                    redis.call('EXPIRE', KEYS[1], ARGV[1])
                end
                return newValue
            """;

    private final ReactiveRedisTemplate<String, ?> reactiveRedisTemplate;

    @Override
    public Mono<Long> incrementWithTtl(String key, long ttl) {
        return reactiveRedisTemplate.execute(RedisScript.of(SCRIPT, Long.class),
                        List.of(key),
                        List.of(String.valueOf(ttl)))
                .single();
    }
}
