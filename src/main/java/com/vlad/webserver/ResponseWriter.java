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
        bufferedWriter.write("\n\r");
        bufferedWriter.write(content);
        bufferedWriter.write("\n\r");

    }

    public void writeNotFoundResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 404 NotFound\n");
        bufferedWriter.write("\n\r");
    }

    public void writeBadRequestResponse() throws IOException {
        bufferedWriter.write("HTTP/1.1 400 BadRequest\n");
        bufferedWriter.write("\n\r");
    }
}
