package com.vlad.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private Map<String, String> headers = new HashMap<>();
    private Request request = new Request();

    public Request parseRequest(BufferedReader reader) throws IOException {

            injectUrlAndMethod(request, reader.readLine());
            injectHeaders(request, reader);
            return request;
    }

    private void injectUrlAndMethod(Request request, String requestLine) {
            String[] requestParams = requestLine.split(" ");
            if (requestParams[0].equals("GET")) {
                request.setMethod(HttpMethod.GET);
                request.setUrl(requestParams[1]);
            }
    }

    private void injectHeaders(Request request, BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("")) {
            if (!line.equals("")) {
                String[] lineParams = line.split(": ");
                headers.put(lineParams[0], lineParams[1]);
                request.setHeaders(headers);
            }
            System.out.println(line);
        }
    }
}
