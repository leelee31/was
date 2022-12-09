package servlet.handler;

import controller.Controller;
import http.HttpRequest;

import java.lang.reflect.InvocationTargetException;

public interface HandlerMapping {
    void addHandlerMapping(String url, String clazz);
    Controller getHandler(HttpRequest request) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException;
}