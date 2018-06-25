package com.vlad.webserver;

import com.sun.org.apache.regexp.internal.RE;

import java.io.*;

public class ResponseWriter {
  private BufferedOutputStream bufferedWriter;
  private ResourceReader resourceReader;
  private BufferedOutputStream bufferedOutputStream;
  private Request request;


  public ResponseWriter(BufferedOutputStream writer, ResourceReader resourceReader, Request request) {
    this.resourceReader = resourceReader;
    this.bufferedWriter = writer;
    this.bufferedOutputStream = writer;
    this.request = request;
  }

  public void sendContent() throws IOException {
    resourceReader.readContent(request.getUrl(), bufferedOutputStream);
    bufferedWriter.write("\n\r".getBytes());
  }

  public void writeSuccessResponse() throws IOException {
    bufferedWriter.write("HTTP/1.1 200 OK\n".getBytes());
    bufferedWriter.write("\n\r".getBytes());
  }

  public void writeNotFoundResponse() throws IOException {
    bufferedWriter.write("HTTP/1.1 404 NotFound\n".getBytes());
    bufferedWriter.write("\n\r".getBytes());
  }

  public void writeBadRequestResponse() throws IOException {
    bufferedWriter.write("HTTP/1.1 400 BadRequest\n".getBytes());
    bufferedWriter.write("\n\r".getBytes());
  }
}
