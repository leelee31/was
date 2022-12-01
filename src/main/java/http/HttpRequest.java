package http;

public class HttpRequest {
    private final HttpRequestLine line;
    private final HttpRequestHeader header;
    private final HttpRequestBody body;

    public HttpRequest(HttpRequestLine line, HttpRequestHeader header, HttpRequestBody body) {
        this.line = line;
        this.header = header;
        this.body = body;
    }

    public static HttpRequest create(HttpRequestLine line, HttpRequestHeader header, HttpRequestBody body) {
        return new HttpRequest(line, header, body);
    }

    public HttpRequestLine getLine() {
        return line;
    }

    public HttpRequestHeader getHeader() {
        return header;
    }

    public String getMethod() {
        return line.getMethod();
    }

    public String getPath() {
        return line.getPath();
    }


    @Override
    public String toString() {
        return "HttpRequest{" +
                "line=" + line.toString() +
                ", header=" + header.toString() +
                ", body=" + body.toString() +
                '}';
    }
}