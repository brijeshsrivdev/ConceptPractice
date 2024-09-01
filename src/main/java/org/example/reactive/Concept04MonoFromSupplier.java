package org.example.reactive;

import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;


import static org.example.reactive.ReactiveUtils.faker;
import static org.example.reactive.ReactiveUtils.onNext;

public class Concept04MonoFromSupplier {
    public static void main(String[] args) {
        // use just only when you have data already
        // Mono<String> mono = Mono.just(getName());

        Supplier<String> stringSupplier = () -> getName();

        // Use supplier when jus to deal with data.
        Mono.fromSupplier(stringSupplier).subscribe(onNext());


        Callable<String> stringCallable = () -> getName();
        // Use when dealing want to task get it done via another thread
        // where mostly chances are exception will be thrown like
        // doing I/O
        Mono.fromCallable(stringCallable)
                .subscribe(
                        onNext()
                );
    }
    private static String getName(){
        System.out.println("Generating name..");
        return faker().name().fullName();
    }
}
