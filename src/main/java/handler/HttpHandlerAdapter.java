package handler;

import controller.Controller;
import http.HttpRequest;
import http.HttpResponse;

import servlet.ModelAndView;

public class HttpHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpRequest req, HttpResponse res, Object handler) {
        if (!support(handler)) {
            throw new IllegalArgumentException("not support handler");
        }
        return ((Controller) handler).handleRequest(req, res);
    }
}