package com.vlad.webserver;

import java.io.*;

public class ResourceReader {
    private String webAppPath;

    ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public InputStream readContent(String content) throws FileNotFoundException {
        File resourcePath = new File(webAppPath + content);
        try  {
            return new FileInputStream(resourcePath);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }
}
