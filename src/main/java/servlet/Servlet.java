package servlet;

import http.HttpRequest;
import http.HttpResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Servlet {
    void init();
    void service(HttpRequest req, HttpResponse res) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException;
    void destroy();
}