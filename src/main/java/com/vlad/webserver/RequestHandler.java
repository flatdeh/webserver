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
        ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStream);
        try {
            Request request = requestParser.parseRequest(reader);
            try (InputStream inputStream = resourceReader.readContent(request.getUrl())) {
                responseWriter.writeSuccessResponse(inputStream);
            } catch (IOException e) {
                responseWriter.writeNotFoundResponse();
            }
        } catch (Exception exception) {
            responseWriter.writeBadRequestResponse();
        }
    }
}
