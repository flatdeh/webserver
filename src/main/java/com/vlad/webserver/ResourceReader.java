package com.vlad.webserver;

import java.io.*;

public class ResourceReader {
  private String resourcePath;
  private BufferedInputStream bufferedInputStream;

  ResourceReader(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public void readContent(String path, BufferedOutputStream bufferedOutputStream) throws IOException {
    File file = new File(resourcePath + path);

    try (InputStream fileReader = new FileInputStream(file)) {
      int count;
      byte[] bytesBuffer = new byte[100];
      while ((count = fileReader.read(bytesBuffer)) != -1) {
        bufferedOutputStream.write(bytesBuffer, 0, count);
      }
      bufferedOutputStream.flush();
    }
  }
}
