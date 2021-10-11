package ru.digitalleague.net.framework.mvc_framework;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import static java.lang.Thread.NORM_PRIORITY;

public class FrameworkMVCManager {


    private static FrameworkMVCManager frameworkMVCManager;
    private static FMVCEndpointList endpointList;
    private static FMVCServer server;

    private FrameworkMVCManager() {
    }

    public static FrameworkMVCManager getInstance() {
        if (frameworkMVCManager == null) {
            frameworkMVCManager = new FrameworkMVCManager();
        }
        return frameworkMVCManager;
    }

    public FrameworkMVCManager addEndpoint(EndpointMethods endpointMethods) {
        if (endpointList == null){
            endpointList = FMVCEndpointList.getInstance();
        }
        endpointList.getControllers().put(endpointMethods.getPath(), endpointMethods);
        return frameworkMVCManager;
    }

    public boolean removeEndpoint(String endpoint) {
        if (endpointList == null){
            return false;
        }
        if (endpointList.getControllers().containsKey(endpoint)) {
            endpointList.getControllers().remove(endpoint);
            return true;
        } else {
            return false;
        }
    }

    public FrameworkMVCManager startWorkingWithPort(int port){
        ServerSocket srvSocket = null;
        try {
            try {
                InetAddress ia = InetAddress.getByName("localhost");
                srvSocket = new ServerSocket(port, 0, ia);
                while(true) {
                    Socket socket = srvSocket.accept();
                    server = new FMVCServer(socket, port, endpointList);
                    server.setDaemon(true);
                    server.setPriority(NORM_PRIORITY);
                    server.start();
                }
            } catch(Exception e) {
                System.out.println("Exception : " + e);
            }
        } finally {
            try {
                if (srvSocket != null)
                    srvSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
        return frameworkMVCManager;
    }

}