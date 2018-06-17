package com.vlad.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static final String BAD_REQUEST = "HTTP/1.1 400 Bad Request\n";
    public static final String OK = "HTTP/1.1 200 OK\n";
    public static final String NOT_FOUND = "HTTP/1.1 404 NotFound\n";
    private int port;
    private String webAppPath;
    private String resource;

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String appPath) {
        this.webAppPath = appPath;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    String message = bufferedReader.readLine();
                    if (message != null) {
                        String[] parseMessage = message.split(" ");

                        if (parseMessage[0].equals("GET")) {
                            resource = webAppPath + parseMessage[1];
                            get(bufferedWriter, resource);
                        } else {
                            send(bufferedWriter , BAD_REQUEST);
                        }
                    }
                }
            }
        }
    }

    private void get(BufferedWriter bufferedWriter, String resource) throws IOException {
        File file = new File(resource);
        if (!file.exists()) {
            send(bufferedWriter, NOT_FOUND);
        } else {
            send(bufferedWriter, OK);

            try(BufferedReader fileStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line;
                while ((line = fileStream.readLine()) != null) {
                    bufferedWriter.write(line);
                }
                bufferedWriter.newLine();
            }
        }
    }

    private void send(BufferedWriter bufferedWriter, String requestCode) throws IOException {
        bufferedWriter.write(requestCode);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
