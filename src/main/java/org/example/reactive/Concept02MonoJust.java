package org.example.reactive;

import reactor.core.publisher.Mono;

public class Concept02MonoJust {
    public static void main(String[] args) {
        // publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        mono.subscribe(i -> System.out.println("Received : " + i));
    }
}
