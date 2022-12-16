package servlet.handler;

import controller.Controller;
import http.HttpRequest;
import servletcontainer.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class UrlControllerHandlerMapping implements HandlerMapping {
    private final Map<String, String> handlerNameMap = new HashMap<>();
    private static final String DEFAULT_HANDLER = "controller.HelloController";

    public UrlControllerHandlerMapping() {
        initHandlerNameMap();
    }

    private void initHandlerNameMap() {
        handlerNameMap.put("/", DEFAULT_HANDLER);
        handlerNameMap.put("/hello", "controller.HelloController");
        handlerNameMap.put("/time", "controller.TimeController");
    }

    public Controller getHandler(HttpRequest request) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String path = request.getPath();
        if (!handlerNameMap.containsKey(path)) {
            throw new IllegalArgumentException("No Handler Found");
        }
        String className = handlerNameMap.get(path);
        return getHandleBean(className);
    }

    public void addHandlerMapping(String url, String clazz) {
        handlerNameMap.put(url, clazz);
    }

    private Controller getHandleBean(String path) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return ApplicationContext.getApplicationContext().getBean(path, Controller.class);
    }
}