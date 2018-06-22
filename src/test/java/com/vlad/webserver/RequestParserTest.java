package com.vlad.webserver;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RequestParserTest {
    RequestParser requestParser = new RequestParser();

    @Test
    public void testParseRequest() throws IOException {
        File file = new File ("src/test/resources/request.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            Request request = requestParser.parseRequest(bufferedReader);
            System.out.println(request.getUrl());
            System.out.println(request.getMethod());
            System.out.println(request.getHeaders());
        }

    }


}