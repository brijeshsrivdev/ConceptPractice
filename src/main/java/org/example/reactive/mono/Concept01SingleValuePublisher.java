package org.example.reactive.mono;

import java.util.stream.Stream;

public class Concept01SingleValuePublisher {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * 2;
                });

        //System.out.println(stream);
        stream.forEach(System.out::println);
    }
}
