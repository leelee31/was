package servlet.handler;

import controller.Controller;
import http.HttpRequest;
import servletcontainer.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class UrlControllerHandlerMapping implements HandlerMapping {
    private final Map<String, String> handlerNameMap = new HashMap<>();
    private final String DEFAULT_PATH = "/";

    public UrlControllerHandlerMapping() {
        initHandlerNameMap();
    }

    private void initHandlerNameMap() {
        handlerNameMap.put("/", "controller.HelloController");
        handlerNameMap.put("/hello", "controller.HelloController");
        handlerNameMap.put("/time", "controller.TimeController");
    }

    public Controller getDefaultHandler() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String className = handlerNameMap.get(DEFAULT_PATH);
        return getHandleBean(className);
    }

    public Controller getHandler(HttpRequest request) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String path = request.getPath();
        if (!handlerNameMap.containsKey(path)) {
            return getDefaultHandler();
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