package com.vlad.webserver;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {
    private BufferedWriter bufferedWriter;

    public ResponseWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void writeSuccessResponse(String content) throws IOException {
        bufferedWriter.write("HTTP/1.1 200 OK\n");
        bufferedWriter.newLine();
        bufferedWriter.write(content);
        bufferedWriter.newLine();

    }

    public void writeNotFoundResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 404 NotFound\n");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
    }

    public void writeBadRequestResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 400 BadRequest\n");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
    }

    public void writeStatusLine() throws IOException {

    }

    public void writeHeaders() {

    }
}
