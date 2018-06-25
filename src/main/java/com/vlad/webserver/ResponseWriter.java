package com.vlad.webserver;

import java.io.*;

public class ResponseWriter {
    private BufferedOutputStream bufferedWriter;
    private ResourceReader resourceReader;
    private BufferedOutputStream bufferedOutputStream;
    private String requestUrl;
    private byte[] newLine = "\r\n".getBytes();
    private byte[] successResponse = "HTTP/1.1 200 OK\n".getBytes();
    private byte[] NotFoundResponse = "HTTP/1.1 404 NotFound\n".getBytes();
    private byte[] BadRequestResponse = "HTTP/1.1 400 BadRequest\n".getBytes();


    public ResponseWriter(BufferedOutputStream writer, ResourceReader resourceReader, String requestUrl) {
        this.resourceReader = resourceReader;
        this.bufferedWriter = writer;
        this.bufferedOutputStream = writer;
        this.requestUrl = requestUrl;
    }

    public void sendContent() throws IOException {
        resourceReader.readContent(requestUrl, bufferedOutputStream);
        bufferedWriter.write(newLine);
    }

    public void writeSuccessResponse() throws IOException {
        bufferedWriter.write(successResponse);
        bufferedWriter.write(newLine);
    }

    public void writeNotFoundResponse() throws IOException {
        bufferedWriter.write(NotFoundResponse);
        bufferedWriter.write(newLine);
    }

    public void writeBadRequestResponse() throws IOException {
        bufferedWriter.write(BadRequestResponse);
        bufferedWriter.write(newLine);
    }
}
