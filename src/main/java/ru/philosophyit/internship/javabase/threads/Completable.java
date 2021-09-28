package ru.philosophyit.internship.javabase.threads;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Completable {
    // Почему в выводе нет "Hello world"?
    //необходимо явно вызвать результат работы потока, порождаемого CompletableFuture, что может быть сделано,
    //к примеру, при помощи метода join()

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println("first produced");
            return "Hello ";
        }).thenCompose(first -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return first + "world";
        }))
        .thenApply(Function.identity())
        .thenAccept(System.out::println);

    }
}
