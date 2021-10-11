package ru.digitalleague.net.framework.framework_test;

import ru.digitalleague.net.framework.mvc_framework.EndpointMethods;
import ru.digitalleague.net.framework.mvc_framework.FMVCEndpointList;


public class EndpointHunter implements EndpointMethods {

    public String path = "/endpoint-hunter";

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String doGet() {
        return "HTTP/1.0 200 OK\r\n" +
                String.format("Date: %s\r\n", FMVCEndpointList.getServerTime())+
                "\r\n"+
                "Greetings! Your get-request brought you here";
    }

    @Override
    public String doPost() {
        return "HTTP/1.0 200 OK\r\n" +
                String.format("Date: %s\r\n", FMVCEndpointList.getServerTime())+
                "\r\n"+
                "Greetings! Your post-request brought you here";
    }

    @Override
    public String doDelete() {
        return "HTTP/1.0 200 OK\r\n" +
                String.format("Date: %s\r\n", FMVCEndpointList.getServerTime())+
                "\r\n"+
                "Greetings! Your delete-request brought you here";
    }
}
