package org.example.reactive;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

import static org.example.reactive.Concept04MonoFromSupplierRefactoring.sleepMillis;

public class ReactiveUtils {
    public static Consumer<Object> onNext(){
        return o -> System.out.println("Received : " + o);
    }

    public static Consumer<Throwable> onError(){
        return e -> System.out.println("ERROR : " + e.getMessage());
    }

    public static Runnable onComplete(){
        return () -> System.out.println("Completed");
    }

    public static Faker faker() {
        final Faker FAKER = Faker.instance();
        return FAKER;
    }

    public static void sleepSeconds(int seconds){
        sleepMillis(seconds * 1000);
    }

}
