package ru.digitalleague.net.http;

import ru.digitalleague.net.framework.mvc_framework.CustomHttpRequest;
import ru.digitalleague.net.framework.mvc_framework.FrameworkMVCManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpServer {

    private ServerSocket serverSocket;
    private boolean started = false;

    public void start(int port) throws IOException {
        /// открываем порт на прослушивание
        serverSocket = new ServerSocket(port);
        started = true;

        /// вечно ждём входящие подключения
        while (true) {
            /// блокирующая операция получения клиентского сокета
            /// сработает ТиТТК к нам кто-нибудь подключится
            Socket clientSocket = serverSocket.accept();
            System.out.println("Incoming connection:\n");

            /// инит ввода-вывода
            try (InputStream in = clientSocket.getInputStream();) {
                try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));) {
                    /// вычитываем полностью полученный запрос, выводя в консоль построчно
                    CustomHttpRequest httpRequest = new CustomHttpRequest();
                    if (in.available() > 0) {
                        System.out.println(new String(in.readNBytes(in.available())));
                    }
                    out.write("HTTP/1.0 200 OK\r\n");
                    out.write(String.format("Date: %s\r\n", getServerTime()));
                    out.write("\r\n");
                    out.write("HELLO WORLD!");

                    System.out.println("\nTerminating connection...\n\n");

                    out.close();
                    in.close();
                    clientSocket.close();
                }
            }

        }
    }

    private String getServerTime() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat.format(calendar.getTime());
    }

    public void stop() {
        if (started) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {}
        }
    }
}