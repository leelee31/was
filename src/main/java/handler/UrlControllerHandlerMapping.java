package handler;

import controller.Controller;
import http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class UrlControllerHandlerMapping implements HandlerMapping {
    private final static Logger logger = LoggerFactory.getLogger(UrlControllerHandlerMapping.class);
    private final Map<String, String> handlerNameMap = new HashMap<>();
    private final Map<String, Controller> handlerMap = new HashMap<>();
    private final String DEFAULT_PATH = "/";

    public Controller getDefaultHandler() {
        if (handlerMap.get(DEFAULT_PATH) == null) {
            generateHandler(DEFAULT_PATH);
        }
        return handlerMap.get(DEFAULT_PATH);
    }

    public Controller getHandler(HttpRequest request) {
        String path = request.getPath();

        if (!handlerNameMap.containsKey(path)) {
            return getDefaultHandler();
        }

        if (handlerMap.get(path) == null) {
            generateHandler(path);
        }

        return handlerMap.get(path);
    }

    public void addHandlerMapping(String url, String clazz) {
        handlerNameMap.put(url, clazz);
    }

    private void generateHandler(String path) {
        try {
            String className = handlerNameMap.get(path);
            Class clazz = Class.forName(className);
            handlerMap.put(path, (Controller) clazz.getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage());
        }
    }
}
