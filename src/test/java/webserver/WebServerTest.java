package webserver;

import org.junit.Test;

public class WebServerTest {
    @Test
    public void webServer_start(){
        WebServer ws = new WebServer();
        ws.start();
    }
}