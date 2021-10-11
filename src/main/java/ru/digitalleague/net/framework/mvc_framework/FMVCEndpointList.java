package ru.digitalleague.net.framework.mvc_framework;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class FMVCEndpointList {

    @Getter
    @Setter
    private HashMap<String, EndpointMethods> controllers = new HashMap<>();
    private static FMVCEndpointList endpointList;
    private final String  error404 =
            "HTTP/1.0 404 Not Found\r\n" +
                    String.format("Date: %s\r\n", getServerTime()) +
                    "\r\n"+
                    "Someone tricked you:  the path you need does not exist!";
    private final String  error405 =
            "HTTP/1.0 405 Method Not Allowed\r\n" +
                    String.format("Date: %s\r\n", getServerTime()) +
                    "\r\n"+
                    "Humble yourself! Method not allowed!";

    private FMVCEndpointList() {
        this.controllers = new HashMap<>();
    }

    public static FMVCEndpointList getInstance() {
        if (endpointList == null) {
            endpointList = new FMVCEndpointList();
        }
        return endpointList;
    }

    public String checkEndpoint(String path, String method){
        if (controllers.containsKey(path)){
            EndpointMethods endpointMethods = controllers.get(path);
            String serverAnswer = execute(method, endpointMethods);
            if (serverAnswer.equals("Method not allowed")){
                return error405;
            }else{
                return serverAnswer;
            }
        } else {
            return error404;
        }
    }

    private String execute(String method, EndpointMethods endpointMethods){
        switch (method){
            case ("GET"):
                return endpointMethods.doGet();
            case ("POST"):
                return endpointMethods.doPost();
            case ("DELETE"):
                return endpointMethods.doDelete();
            default:
                return "Method not allowed";
        }
    }

    public static String getServerTime() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat.format(calendar.getTime());
    }
}
