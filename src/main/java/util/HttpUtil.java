package util;

import http.*;

import java.io.*;
import java.util.Optional;

public class HttpUtil {

    public static HttpRequest parseRequest(String str) throws IOException {
        InputStream is = new ByteArrayInputStream(str.getBytes());
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        return parseRequest(in);
    }

    public static HttpRequest parseRequest(BufferedReader in) throws IOException {
        HttpRequestLine line = parseRequestLine(in);
        HttpRequestHeader header = parseRequestHeader(in);
        HttpRequestBody body = parseRequestBody(in, header.getContentLength());
        HttpRequest hr = HttpRequest.create(line, header, body);
        return hr;
    }

    public static HttpRequestLine parseRequestLine(BufferedReader in) throws IOException {
        return HttpRequestLine.create(in);
    }

    public static HttpRequestHeader parseRequestHeader(BufferedReader in) throws IOException {
        return HttpRequestHeader.create(in);
    }

    public static HttpRequestBody parseRequestBody(BufferedReader in, Optional<String> contentLength) throws IOException {
        return HttpRequestBody.create(in, contentLength);
    }
}
