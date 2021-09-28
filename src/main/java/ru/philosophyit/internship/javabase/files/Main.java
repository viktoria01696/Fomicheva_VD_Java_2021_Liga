package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Main {

    static int index = 0;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String... args) throws IOException {
        System.out.println(Files.readString(Path.of("src/main/resources/hello.txt")));

        // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        // например
        // src/main/java/ru/philosophyit/internship/javabase/files/Main.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/DeadLock.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/LiveLock.java
        // src/main/java/ru/philosophyit/internship/javabase/threads/Completable.java
        // и т.д.
        /// Более удачные оформления вывода в консоль приветствуются

        File myFile = new File("src/");
        System.out.println(printDirectories(myFile, 0));
    }

    public static String printDirectories(File filePath, int index) {

        stringBuilder.append(getPackageLevel(index))
                .append("+--")
                .append(filePath.getName())
                .append("/")
                .append("\n");
        for (File file : Objects.requireNonNull(filePath.listFiles())) {
            if (file.isDirectory()) {
                printDirectories(file, index + 1);
            } else {
                printFile(file, index + 1);
            }
        }
        return stringBuilder.toString();
    }

    private static void printFile(File file, int index) {
        stringBuilder.append(getPackageLevel(index))
                .append("   ")
                .append(file.getName())
                .append("\n");
    }

    private static String getPackageLevel(int index) {
        return "|  ".repeat(index);
    }

}
