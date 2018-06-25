package com.vlad.webserver;

import org.junit.Test;

import java.io.IOException;

public class WebServerITest {

    @Test
    public void testServer() throws IOException {
        WebServer server = new WebServer();
        server.setPort(3000);
        server.setWebAppPath("src/test/resources/webapp");
        server.start();
    }

}