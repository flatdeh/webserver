package com.vlad.webserver;

import java.io.*;

public class RequestHandler {
  private BufferedReader reader;
  private BufferedOutputStream writer;
  private ResourceReader resourceReader;

  public RequestHandler(BufferedReader bufferedReader, BufferedOutputStream bufferedOutputStream, ResourceReader resourceReader) {
    this.reader = bufferedReader;
    this.writer = bufferedOutputStream;
    this.resourceReader = resourceReader;
  }

  public void handle() throws IOException {
    RequestParser requestParser = new RequestParser();

  Request request = requestParser.parseRequest(reader);
  ResponseWriter responseWriter = new ResponseWriter(writer, resourceReader, request);
  try {
    responseWriter.writeSuccessResponse();
    responseWriter.sendContent();
  } catch (IOException e) {
    responseWriter.writeNotFoundResponse();
  }

  }

  public ResourceReader getResourceReader() {
    return resourceReader;
  }

  public void setResourceReader(ResourceReader resourceReader) {
    this.resourceReader = resourceReader;
  }
}
