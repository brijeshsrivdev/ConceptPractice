package org.example.reactive.mono;

import reactor.core.publisher.Mono;

import static org.example.reactive.ReactiveUtils.faker;

import reactor.core.scheduler.Schedulers;

public class Concept04MonoFromSupplierRefactoring {
    public static void main(String[] args) {


        getName();
        System.out.println(Thread.currentThread().getName());
        String name = getName()
                .subscribeOn(Schedulers.boundedElastic())
                .log()
                .block();
        System.out.println(name);
        getName();

        sleepSeconds(4);
    }
    private static Mono<String> getName(){
        System.out.println("entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            System.out.println(Thread.currentThread().getName());
            sleepSeconds(3);
            return faker().name().fullName();
        }).map(String::toUpperCase);
    }

    public static void sleepSeconds(int seconds){
        sleepMillis(seconds * 1000);
    }

    public static void sleepMillis(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

