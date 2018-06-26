package com.vlad.webserver;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class RequestParserTest {
    private RequestParser requestParser = new RequestParser();

    @Test
    public void testParseRequest() throws IOException {
        String requestText = "GET /index.html HTTP/1.1\n" +
                "Host: alizar.habrahabr.ru\n" +
                "Server: nginx/1.2.1\n" +
                "Date: Sat, 08 Mar 2014 22:53:46 GMT\n" +
                "Content-Type: application/octet-stream\n" +
                "Content-Length: 7\n" +
                "Last-Modified: Sat, 08 Mar 2014 22:53:30 GMT\n" +
                "Connection: keep-alive\n" +
                "Accept-Ranges: bytes\n" +
                "\n";
        try (BufferedReader bufferedReader = new BufferedReader(new CharArrayReader(requestText.toCharArray()))) {
            Request request = requestParser.parseRequest(bufferedReader);
            assertEquals("/index.html", request.getUrl());
            assertEquals(HttpMethod.GET, request.getMethod());
        }
    }
}