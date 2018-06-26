package com.vlad.webserver;

import java.io.*;

public class ResponseWriter {
    private static final int BYTES_BUFFER_SIZE = 256;
    private BufferedOutputStream bufferedOutputStream;
    private byte[] newLine = "\r\n".getBytes();
    private byte[] successResponse = "HTTP/1.1 200 OK\n".getBytes();
    private byte[] notFoundResponse = "HTTP/1.1 404 NotFound\n".getBytes();
    private byte[] badRequestResponse = "HTTP/1.1 400 BadRequest\n".getBytes();


    public ResponseWriter(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }

    public void writeSuccessResponse(InputStream content) throws IOException {
        bufferedOutputStream.write(successResponse);
        bufferedOutputStream.write(newLine);
        int count;
        byte[] bytesBuffer = new byte[BYTES_BUFFER_SIZE];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(content);
        while ((count = bufferedInputStream.read(bytesBuffer)) != -1) {
            bufferedOutputStream.write(bytesBuffer, 0, count);
        }
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
