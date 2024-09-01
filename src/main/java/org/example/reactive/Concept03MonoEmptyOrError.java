package org.example.reactive;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Concept03MonoEmptyOrError {
    public static void main(String[] args) {
        userList(1)
                .subscribe(
                        onNext(),
                        onError(),
                        onComplete()
                );
    }

    private static Mono<String> userList(int userId) {
        // 1
        if (userId == 1) {
            return Mono.just(faker().name().firstName());
        } else if (userId == 2) {
            return Mono.empty(); // null
        } else
            return Mono.error(new RuntimeException("Not in the allowed range"));
    }

    public static Faker faker() {
        final Faker FAKER = Faker.instance();
        return FAKER;
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
