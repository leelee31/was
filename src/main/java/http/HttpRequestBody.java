package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class HttpRequestBody {
    private final String body;

    public HttpRequestBody(String body) {
        this.body = body;
    }

    public static HttpRequestBody create(BufferedReader in, Optional<String> contentLength) throws IOException {
        String body;
        if(!contentLength.isPresent())
            body = "";
        else
            body = in.readLine();
        return new HttpRequestBody(body);
    }
}