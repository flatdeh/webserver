package com.vlad.webserver;

import java.io.*;

public class ResourceReader {
    private String resourcePath;

    ResourceReader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String readContent(String path) throws IOException {
        File file = new File(resourcePath + path);

        try(BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            String content = "";
            while ((line = fileReader.readLine()) != null) {
                content += line;
            }
            return content;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
