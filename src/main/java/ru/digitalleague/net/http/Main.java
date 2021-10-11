package ru.digitalleague.net.http;

import ru.digitalleague.net.framework.mvc_framework.FrameworkMVCManager;
import ru.digitalleague.net.framework.framework_test.EndpointHunter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        HttpServer server = new HttpServer();
        try {
            server.start(8080);
        } catch (IOException e) {
            System.err.printf("Can't start server: %s%n", e.getMessage());
        } finally {
            server.stop();
        }
    }
}
