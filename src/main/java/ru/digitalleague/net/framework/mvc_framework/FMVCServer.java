package ru.digitalleague.net.framework.mvc_framework;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class FMVCServer extends Thread{

    private int port;
    private Socket socket;
    private FMVCEndpointList endpointList;


    public FMVCServer(Socket socket, int port, FMVCEndpointList endpointList){
        this.port = port;
        this.socket = socket;
        this.endpointList = endpointList;
    }

    public void run() {
        try{
            InputStream sin  = socket.getInputStream();
            BufferedWriter sout = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            String line = null;
            while(true) {
                line = new String(sin.readNBytes(sin.available()));
                CustomHttpRequest httpRequest = CustomHttpRequest.parseRequest(line);
                String serverAnswer = endpointList.checkEndpoint(httpRequest.getPath(),
                        httpRequest.getMethod());
                sout.write(serverAnswer);
                sout.flush();
                socket.close();
            }
        } catch (Exception ignored) {}
    }
}
