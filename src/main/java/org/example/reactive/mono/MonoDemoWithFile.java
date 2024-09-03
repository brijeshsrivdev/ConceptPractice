package org.example.reactive.mono;

import org.example.reactive.ReactiveUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MonoDemoWithFile {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        write("file3.txt", "This is file3")
                .subscribe(ReactiveUtils.onNext(), ReactiveUtils.onError(), ReactiveUtils.onComplete());

        read("file3.txt")
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(ReactiveUtils.onNext(), ReactiveUtils.onError(), ReactiveUtils.onComplete());

        delete("file3.txt")
                .subscribe(ReactiveUtils.onNext(), ReactiveUtils.onError(), ReactiveUtils.onComplete());
    }

    private static final Path PATH = Paths.get("src/main/resources/demo");


    public static Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    private static void writeFile(String fileName, String content) {
        try {
            System.out.println("File processing thread :: " + Thread.currentThread().getName());
            Files.writeString(PATH.resolve(fileName),
                    content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<String> read(String fileName) {
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    private static String readFile(String fileName) {
        try {
            System.out.println(Thread.currentThread().getName());
            var res = Files.readString(PATH.resolve(fileName));
            System.out.println(res);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }


    private static void deleteFile(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
