package com.vlad.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public Request parseRequest(BufferedReader reader) throws IOException {
        Request request = new Request();
        injectUrlAndMethod(request, reader.readLine());
        injectHeaders(request, reader);
        return request;
    }

    private void injectUrlAndMethod(Request request, String requestLine) {
        try {
            String[] requestParams = requestLine.split(" ");
            HttpMethod httpMethod = HttpMethod.getHttpMethodByName(requestParams[0]);
            request.setMethod(httpMethod);
            request.setUrl(requestParams[1]);
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    private void injectHeaders(Request request, BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line;
        try {
            while (!(line = reader.readLine()).isEmpty()) {
                String[] lineParams = line.split(": ");
                headers.put(lineParams[0], lineParams[1]);
                request.setHeaders(headers);
            }
        } catch (IOException e) {
            throw new IOException();
        } catch (NullPointerException npe) {
            throw new NullPointerException();
        }
    }
}
