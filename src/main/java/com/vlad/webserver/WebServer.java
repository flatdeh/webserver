package com.vlad.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
  private int port;
  private ResourceReader resourceReader;

  public void start() throws IOException {
    try (ServerSocket server = new ServerSocket(port)) {
      while (true) {
        try (Socket socket = server.accept();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream())) {

          RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedOutputStream, resourceReader);
          requestHandler.handle();
        }
      }
    }
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setWebAppPath(String resource) {
    this.resourceReader = new ResourceReader(resource);
  }
}
