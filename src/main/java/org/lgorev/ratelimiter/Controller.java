package org.lgorev.ratelimiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
public class Controller {

    @PostMapping
    public Mono<String> create(@RequestBody Mono<String> request) {
        return request
                .doOnNext(it -> log.info("request"))
                .map(it -> "hello " + it);
    }

    @GetMapping
    public Mono<String> get(@RequestBody Mono<String> request) {
        return request
                .map(it -> "hello " + it);
    }
}
