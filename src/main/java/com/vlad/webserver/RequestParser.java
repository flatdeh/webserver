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
        String[] requestParams = requestLine.split(" ");
        HttpMethod httpMethod = HttpMethod.getHttpMethodByName(requestParams[0]);
        request.setMethod(httpMethod);
        request.setUrl(requestParams[1]);
    }

    private void injectHeaders(Request request, BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] lineParams = line.split(": ");
            headers.put(lineParams[0], lineParams[1]);
            request.setHeaders(headers);
        }
    }
}
