package com.vlad.webserver;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class RequestParserTest {
    private RequestParser requestParser = new RequestParser();

    @Test
    public void testParseRequest() throws IOException {
        File file = new File ("src/test/resources/request.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            Request request = requestParser.parseRequest(bufferedReader);
            assertEquals("/index.html", request.getUrl());
            assertEquals(HttpMethod.GET, request.getMethod());
        }

    }
}