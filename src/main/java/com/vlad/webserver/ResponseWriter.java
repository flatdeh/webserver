package com.vlad.webserver;

import java.io.*;

public class ResponseWriter {
    private static final int BYTES_BUFFER_SIZE =        256;
    private static final byte[] NEW_LINE =              "\r\n".getBytes();
    private static final byte[] SUCCESS_RESPONSE =      "HTTP/1.1 200 OK\n".getBytes();
    private static final byte[] NOT_FOUND_RESPONSE =    "HTTP/1.1 404 NotFound\n".getBytes();
    private static final byte[] BAD_REQUEST_RESPONSE =  "HTTP/1.1 400 BadRequest\n".getBytes();
    private BufferedOutputStream bufferedOutputStream;


    public ResponseWriter(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }

    public void writeSuccessResponse() throws IOException {
        bufferedOutputStream.write(SUCCESS_RESPONSE);
        bufferedOutputStream.write(NEW_LINE);
    }

    public void writeContent(InputStream content) throws IOException {
        int count;
        byte[] bytesBuffer = new byte[BYTES_BUFFER_SIZE];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(content);
        while ((count = bufferedInputStream.read(bytesBuffer)) != -1) {
            bufferedOutputStream.write(bytesBuffer, 0, count);
        }
        bufferedOutputStream.write(NEW_LINE);
    }


    public void writeNotFoundResponse() throws IOException {
        bufferedOutputStream.write(NOT_FOUND_RESPONSE);
        bufferedOutputStream.write(NEW_LINE);
    }

    public void writeBadRequestResponse() throws IOException {
        bufferedOutputStream.write(BAD_REQUEST_RESPONSE);
        bufferedOutputStream.write(NEW_LINE);
    }
}
