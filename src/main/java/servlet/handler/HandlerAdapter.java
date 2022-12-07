package servlet.handler;

import http.HttpRequest;
import http.HttpResponse;
import servlet.ModelAndView;

public interface HandlerAdapter {
    boolean support(Object handler);
    ModelAndView handle(HttpRequest req, HttpResponse res, Object handler);
}