package com.vlad.webserver;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ResponseWriterTest {
    private BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("src/test/resources/response.txt")));
    private BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("src/test/resources/response.txt")));
    private ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStream);

    public ResponseWriterTest() throws FileNotFoundException {

    }

    @Test
    public void testWriteSuccessResponse() throws IOException {
        responseWriter.writeSuccessResponse();
        bufferedOutputStream.close();

        int count;
        byte[] bytes = new byte[40];
        while ((count = bufferedInputStream.read(bytes)) != -1) {
            assertEquals("HTTP/1.1 200 OK\n\r\n", new String(bytes, 0, count));
        }
        bufferedInputStream.close();
    }

    @Test
    public void testWriteNotFoundResponse() throws IOException {
        responseWriter.writeNotFoundResponse();
        bufferedOutputStream.close();

        int count;
        byte[] bytes = new byte[40];
        while ((count = bufferedInputStream.read(bytes)) != -1) {
            assertEquals("HTTP/1.1 404 NotFound\n\r\n", new String(bytes, 0, count));
        }
        bufferedInputStream.close();

    }

    @Test
    public void testWriteBadRequestResponse() throws IOException {
        responseWriter.writeBadRequestResponse();
        bufferedOutputStream.close();

        int count;
        byte[] bytes = new byte[40];
        while ((count = bufferedInputStream.read(bytes)) != -1) {
            assertEquals("HTTP/1.1 400 BadRequest\n\r\n", new String(bytes, 0, count));
        }
        bufferedInputStream.close();

    }

    @Test
    public void writeContent() throws IOException {
        responseWriter.writeContent(new FileInputStream(new File("src/test/resources/request.txt")));
        bufferedOutputStream.close();

        int count;
        byte[] bytes = new byte[100];
        count = bufferedInputStream.read(bytes);
        assertEquals("GET /index.html HTTP/1.1\nHost: alizar.habrahabr.ru\nServer: nginx/1.2.1\nDate: Sat, 08 Mar 2014 22:53:", new String(bytes, 0, count));
        bufferedInputStream.close();

    }
}