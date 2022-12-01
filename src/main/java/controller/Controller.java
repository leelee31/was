package controller;

import http.HttpRequest;
import http.HttpResponse;
import servlet.ModelAndView;

public interface Controller {
    ModelAndView handleRequest(HttpRequest req, HttpResponse res);
}