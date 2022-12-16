package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HttpRequestHeader {
    private final Map<String, String> fields;
    private final String host;
    private final String port;

    private HttpRequestHeader(Map<String, String> fields, String host, String port) {
        this.fields = fields;
        this.host = host;
        this.port = port;
    }

    public static HttpRequestHeader create(BufferedReader in) throws IOException {
        Map<String, String> fields = new HashMap<>();
        String host = "", port = "";

        while(in.ready()) {
            String line = in.readLine();
            if (line.equals(HttpExp.BLANK)) {
                break;
            }
            String[] headerTokens = line.split(HttpExp.COLON_SPACE, 2);
            if (headerTokens[0].equals("Host")) {
                String[] hostTokens = headerTokens[1].split(HttpExp.COLON, 2);
                host = hostTokens[0];
                if (hostTokens.length > 1)
                    port = hostTokens[1];
            } else {
                fields.put(headerTokens[0], headerTokens[1]);
            }
        }

        return new HttpRequestHeader(fields, host, port);
    }

    public Optional<String> getContentLength() {
        return Optional.ofNullable(fields.get("content-length"));
    }
}
