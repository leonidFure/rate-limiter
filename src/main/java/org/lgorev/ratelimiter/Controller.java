package org.lgorev.ratelimiter;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RequestMapping("api/v1/orders")
@RestController
public class Controller {

    @PostMapping
    public Mono<String> create(@RequestBody Mono<String> request) {
        return request
                .map(it -> "hello " + it);
    }

    @GetMapping
    public Mono<String> get(@RequestBody Mono<String> request) {
        return request
                .map(it -> "hello " + it);
    }
}
