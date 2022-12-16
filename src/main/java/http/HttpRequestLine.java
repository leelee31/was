package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestLine {
    private final String method;
    private final String url;
    private final String httpVersion;
    private final String path;
    private final Map<String, String> parameters = new HashMap<>();

    private HttpRequestLine(String method, String url, String httpVersion) {
        this.method = method;
        this.url = url;
        String[] uri = url.split(HttpExp.EXIST_QUERY);
        this.path = uri[0];
        if (existParameters(uri)) {
            extractParameter(uri[1], parameters);
        }
        this.httpVersion = httpVersion;
    }

    public static HttpRequestLine create(BufferedReader in) throws IOException {
        String line = in.readLine();
        String[] token = line.split(HttpExp.SPACE, 3);
        return new HttpRequestLine(token[0], token[1], token[2]);
    }

    private static boolean existParameters(String[] uri) {
        return uri.length > 1;
    }

    private static void extractParameter(String uri, Map<String, String> parameters) {
        String[] uris = uri.split("[&]");
        for (String p : uris) {
            String[] keyAndValue = p.split("=");
            if (keyAndValue.length >= 2) {
                parameters.put(keyAndValue[0], keyAndValue[1]);
                continue;
            }
            parameters.put(keyAndValue[0], "");
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getPath() {
        return path;
    }
}