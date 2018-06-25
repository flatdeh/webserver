package com.vlad.webserver;

import java.io.*;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedOutputStream bufferedOutputStream;
    private ResourceReader resourceReader;

    public RequestHandler(BufferedReader bufferedReader, BufferedOutputStream bufferedOutputStream, ResourceReader resourceReader) {
        this.reader = bufferedReader;
        this.bufferedOutputStream = bufferedOutputStream;
        this.resourceReader = resourceReader;
    }

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(reader);

        ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStream, resourceReader, request.getUrl());
        try {
            responseWriter.writeSuccessResponse();
            responseWriter.sendContent();
        } catch (IOException e) {
            responseWriter.writeNotFoundResponse();
        }
    }
}
