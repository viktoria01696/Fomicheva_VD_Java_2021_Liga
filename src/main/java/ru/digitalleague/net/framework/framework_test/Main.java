package ru.digitalleague.net.framework.framework_test;

import ru.digitalleague.net.framework.mvc_framework.FrameworkMVCManager;

public class Main {
    public static void main(String[] args) {

        FrameworkMVCManager frameworkMVCManager = FrameworkMVCManager
                .getInstance()
                .addEndpoint(new EndpointHunter())
                .startWorkingWithPort(8080);
    }
}
