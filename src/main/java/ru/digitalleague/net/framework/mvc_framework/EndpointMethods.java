package ru.digitalleague.net.framework.mvc_framework;

public interface EndpointMethods {

    String getPath();
    default String doGet(){return "Method not allowed";}
    default String doPost(){return "Method not allowed";};
    default String doDelete() {return "Method not allowed";}
}
