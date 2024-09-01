package org.example.reactive;

import reactor.core.publisher.Mono;

public class ConceptLec05MonoFromRunnable {
    public static void main(String[] args) {
        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(ReactiveUtils.onNext(),
                        ReactiveUtils.onError(),
                        () -> {
                            System.out.println("process is done. Sending emails...");
                        });
    }


    private static Runnable timeConsumingProcess(){
        return () -> {
            ReactiveUtils.sleepSeconds(3);
            System.out.println("Operation completed");
        };
    }
}
