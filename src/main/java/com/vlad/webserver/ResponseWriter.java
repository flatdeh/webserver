package com.vlad.webserver;

import java.io.*;

public class ResponseWriter {
    private ResourceReader resourceReader;
    private BufferedOutputStream bufferedOutputStream;
    private String requestUrl;
    private byte[] newLine = "\r\n".getBytes();
    private byte[] successResponse = "HTTP/1.1 200 OK\n".getBytes();
    private byte[] notFoundResponse = "HTTP/1.1 404 NotFound\n".getBytes();
    private byte[] badRequestResponse = "HTTP/1.1 400 BadRequest\n".getBytes();


    public ResponseWriter(BufferedOutputStream bufferedOutputStream, ResourceReader resourceReader, String requestUrl) {
        this.resourceReader = resourceReader;
        this.bufferedOutputStream = bufferedOutputStream;
        this.requestUrl = requestUrl;
    }

    public void sendContent() throws IOException {
        resourceReader.readContent(requestUrl, bufferedOutputStream);
        bufferedOutputStream.write(newLine);
    }

    public void writeSuccessResponse() throws IOException {
        bufferedOutputStream.write(successResponse);
        bufferedOutputStream.write(newLine);
    }

    public void writeNotFoundResponse() throws IOException {
        bufferedOutputStream.write(notFoundResponse);
        bufferedOutputStream.write(newLine);
    }

    public void writeBadRequestResponse() throws IOException {
        bufferedOutputStream.write(badRequestResponse);
        bufferedOutputStream.write(newLine);
    }
}
