package com.vlad.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private int port;
    private ResourceReader resourceReader;

    public void start() throws IOException {
        try(ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try(Socket socket = server.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, resourceReader);
                    requestHandler.handle();

                }
            }
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setWebAppPath(String resource) {
        this.resourceReader = new ResourceReader(resource);
    }
}
