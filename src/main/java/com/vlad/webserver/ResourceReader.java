package com.vlad.webserver;

import java.io.*;

public class ResourceReader {
    private String webAppPath;

    ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void readContent(String content, BufferedOutputStream bufferedOutputStream) throws FileNotFoundException {
        File resourcePath = new File(webAppPath + content);

        try (InputStream fileReader = new FileInputStream(resourcePath)) {
            int count;
            byte[] bytesBuffer = new byte[100];
            while ((count = fileReader.read(bytesBuffer)) != -1) {
                bufferedOutputStream.write(bytesBuffer, 0, count);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
