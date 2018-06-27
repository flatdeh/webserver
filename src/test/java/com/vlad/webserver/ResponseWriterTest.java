package com.vlad.webserver;

import org.junit.Test;

import java.io.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ResponseWriterTest {

    @Test
    public void testWriteSuccessResponse() throws IOException {
        BufferedOutputStream bufferedOutputStreamMock = mock(BufferedOutputStream.class);
        ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStreamMock);
        responseWriter.writeSuccessResponse();

        verify(bufferedOutputStreamMock).write("HTTP/1.1 200 OK\n".getBytes());
        verify(bufferedOutputStreamMock).write("\r\n".getBytes());
    }

    @Test
    public void testWriteNotFoundResponse() throws IOException {
        BufferedOutputStream bufferedOutputStreamMock = mock(BufferedOutputStream.class);
        ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStreamMock);
        responseWriter.writeNotFoundResponse();

        verify(bufferedOutputStreamMock).write("HTTP/1.1 404 NotFound\n".getBytes());
        verify(bufferedOutputStreamMock).write("\r\n".getBytes());;
    }

    @Test
    public void testWriteBadRequestResponse() throws IOException {
        BufferedOutputStream bufferedOutputStreamMock = mock(BufferedOutputStream.class);
        ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStreamMock);
        responseWriter.writeBadRequestResponse();

        verify(bufferedOutputStreamMock).write("HTTP/1.1 400 BadRequest\n".getBytes());
        verify(bufferedOutputStreamMock).write("\r\n".getBytes());
    }

    @Test
    public void testWriteContent() throws IOException {
        byte[] contentExpected = new byte[256];
        byte[] content = "body {font-family: Verdana,\n sans-serif;}".getBytes();
        System.arraycopy(content,0,contentExpected,0,content.length);

        InputStream inputStream = new ByteArrayInputStream(content);
        BufferedOutputStream bufferedOutputStream = mock(BufferedOutputStream.class);
        ResponseWriter responseWriter = new ResponseWriter(bufferedOutputStream);

        responseWriter.writeContent(inputStream);

        verify(bufferedOutputStream).write(contentExpected, 0, 41);
        verify(bufferedOutputStream).write("\r\n".getBytes());
    }

}

