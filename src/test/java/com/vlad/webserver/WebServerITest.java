package com.vlad.webserver;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class WebServerITest {

    @Test
    public void testServer() throws IOException {
        WebServer server = new WebServer();
        server.setPort(3000);
        server.setWebAppPath("src/main/resources/webapp");
        server.start();
    }

}