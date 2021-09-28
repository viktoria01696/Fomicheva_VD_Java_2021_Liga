package ru.philosophyit.internship.javabase.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // Почему при вызове executorService.shutdown(); программа продолжает свое исполнение ?
    // метод shutdown() выполняет остановку объекта ExecutorService, но только после того, как завершены все его потоки,
    // в настоящей программе в пул потоков поступает 16 задач, программа завершится, когда все они отработают

    // Почему если убрать строчку 28 (executorService.shutdown()) программа не прекратит свое исполнение
    // даже после завершения всех тасок в executorService ?
    // потоки в объекте ExecutorService не останавливаются сами и требуют явной остановки, без строчки с методом shutdown()
    // этой остановки не будет, программа продолжит работу, также продолжит свою работу поток-демон


    // Почему при работе тасок executorService в консоль в секунду попадает всего 4 сообщения, тогда как тасок в executorService - 16?
    // при создании объекта ExecutorService при помощи метода newFixedThreadPool указывается, сколько активных потоков может
    // быть одновременно: ограничение в 4 потока с учетом задержки в 1000 ms и обуславливает вывод по 4 строчки

    public static void main(String[] args) {
        startSomeDaemon();

        int num = getThreadsCount();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < num; i++) {
            int captureId = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.err.println(String.format("Hello from %d callable", captureId));
            });
        }
        //executorService.shutdown();
    }

    private static int getThreadsCount() {
        return 16;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            int num = Integer.valueOf(reader.readLine());
//            return num;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
    }

    private static void startSomeDaemon() {
        Thread thread = new Thread(() -> {
            int t = 0;
            while (true) {
                System.err.println(t++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
