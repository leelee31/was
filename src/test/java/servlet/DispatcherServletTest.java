package servlet;

import handler.HandlerMapping;
import http.HttpRequest;
import http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import util.HttpUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class DispatcherServletTest {
    private Servlet ds;
    private HandlerMapping hm;

    @Before
    public void setUp() {
        ApplicationContext ac = ApplicationContext.getApplicationContext();
        ds = DispatcherServlet.getDispatcherServlet(ac);
        hm = ac.getHandlerMapping();
    }

    @Test
    public void dispatch_weatherController_test() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        hm.addHandlerMapping("/weather", "controller.WeatherController");
        String path = "weather";
        String httpMsg = "GET /"+ path + " HTTP/1.0 \r\nHost: localhost:8080 \r\nUser-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 \r\n";
        HttpRequest req = HttpUtil.parseRequest(httpMsg);
        HttpResponse res = new HttpResponse();

        ds.service(req, res);

        assertEquals("200", res.getStatusCode());
    }
}