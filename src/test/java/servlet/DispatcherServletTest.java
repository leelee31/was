package servlet;

import controller.WeatherController;
import servlet.handler.HandlerMapping;
import http.HttpRequest;
import http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import servletcontainer.ApplicationContext;
import util.HttpUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class DispatcherServletTest {
    ApplicationContext ac;
    Servlet ds;
    HandlerMapping hm;
    String httpMethod = "GET /";
    String httpMsg = " HTTP/1.0 \r\nHost: localhost:8080 \r\nUser-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 \r\n";

    @Before
    public void setUp() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ac = ApplicationContext.getApplicationContext();
        ds = DispatcherServlet.getDispatcherServlet(ac);
        hm = ac.getHandlerMapping();
        hm.addHandlerMapping("/weather", "controller.WeatherController");
        ac.registerStorage("controller.WeatherController", new WeatherController());
    }

    @Test
    public void dispatch_weatherController_test() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String path = "weather";
        HttpRequest req = HttpUtil.parseRequest(httpMethod + path + httpMsg);
        HttpResponse res = new HttpResponse();

        ds.service(req, res);

        assertEquals("200", res.getStatusCode());
    }
}