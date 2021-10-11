package ru.digitalleague.net.framework.mvc_framework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Setter
@Getter
@NoArgsConstructor
public class CustomHttpRequest {

    private String method;
    private String path;
    private String pathParam;
    private String version;
    private HashMap<String, String> headers;
    private ArrayList<String> body;

    public CustomHttpRequest(String method, String path, String pathParam,
                             String version, HashMap<String, String> headers, ArrayList<String> body) {
        this.method = method;
        this.path = path;
        this.pathParam = pathParam;
        this.version = version;
        this.headers = headers;
        this.body = body;
    }

    public static CustomHttpRequest parseRequest(String httpRequest){
        String[] request = httpRequest.split("\\n");
        CustomHttpRequest customHttpRequest = new CustomHttpRequest();
        parseRequestLine(customHttpRequest, request[0]);
        parseHeaders(customHttpRequest, request);
        parseBody(customHttpRequest, request);
        return customHttpRequest;
    }

    private static void parseRequestLine(CustomHttpRequest httpRequest, String requestLine){
        String[] subStrings = requestLine.split(" ");
        httpRequest.setMethod(subStrings[0]);
        separatePathParamsAndPath(httpRequest, subStrings[1]);
        httpRequest.setVersion(subStrings[2]);
    }

    private static void separatePathParamsAndPath(CustomHttpRequest httpRequest, String pathString){
        if (pathString.contains("?")){
            String[] subStrings = pathString.split("\\?");
            httpRequest.setPath(subStrings[0]);
            httpRequest.setPathParam(subStrings[1]);
        }else{
            httpRequest.setPath(pathString);
        }
    }

    private static void parseHeaders(CustomHttpRequest httpRequest, String[] request){
        httpRequest.setHeaders(new HashMap<>());
        int count = 1;
        while (true){
            if(request[count].equals("\r")){
                break;
            }
            String[] subStrings = request[count].split(" ");
            httpRequest.getHeaders().put(subStrings[0], subStrings[1]);
            count++;
        }
    }

    private static void parseBody(CustomHttpRequest httpRequest, String[] request){
        httpRequest.setBody(new ArrayList<>());
        int count = httpRequest.getHeaders().size() + 1;
        if (request.length > count){
            for (int i = (count + 1); i < request.length; i++) {
                httpRequest.getBody().add(0, request[i]);
            }
        }
   }
}
