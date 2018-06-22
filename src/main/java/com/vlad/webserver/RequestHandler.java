package com.vlad.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedWriter writer;
    private ResourceReader resourceReader;

    public RequestHandler(BufferedReader bufferedReader, BufferedWriter bufferedWriter, ResourceReader resourceReader) {
        this.reader = bufferedReader;
        this.writer = bufferedWriter;
        this.resourceReader = resourceReader;
    }

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(reader);

        ResponseWriter responseWriter = new ResponseWriter(writer);
        String content = resourceReader.readContent(request.getUrl());
        if (content!=null) {
            responseWriter.writeSuccessResponse(content);
        } else {
            responseWriter.writeNotFoundResponse();
        }
    }

    public ResourceReader getResourceReader() {
        return resourceReader;
    }

    public void setResourceReader(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }
}
