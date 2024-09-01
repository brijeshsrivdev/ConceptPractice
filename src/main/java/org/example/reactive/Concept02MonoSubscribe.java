package org.example.reactive;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Concept02MonoSubscribe {
    public static void main(String[] args) {
        // publisher
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(l -> l / 1);

        // 1
        // mono.subscribe();

        // 2
        mono.subscribe(
                onNext(),
                onError(),
                onComplete()
        );
    }

    public static Consumer<Object> onNext(){
        return o -> System.out.println("Received : " + o);
    }

    public static Consumer<Throwable> onError(){
        return e -> System.out.println("ERROR : " + e.getMessage());
    }

    public static Runnable onComplete(){
        return () -> System.out.println("Completed");
    }
}
