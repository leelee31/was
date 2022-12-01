package servlet;

import http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelAndView {
    private Map<String, Object> model = new HashMap<>();
    private byte[] view;
    private String viewName;
    private HttpStatus status;

    public ModelAndView() {}

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndView(Map<String, Object> model, String viewName) {
        this.model = model;
        this.viewName = viewName;
    }

    public void setView(byte[] view) {
        this.view = view;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public byte[] getView() {
        return view;
    }

    public String getViewName() {
        return viewName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ModelAndView addObject(String name, Object obj) {
        model.put(name, obj);
        return this;
    }

    public String getModelInfo() {
        return model.keySet().stream()
                .map(key -> key + ": " + model.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}