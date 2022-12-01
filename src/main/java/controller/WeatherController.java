package controller;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.ModelAndView;

public class WeatherController implements Controller {
    private final static Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Override
    public ModelAndView handleRequest(HttpRequest req, HttpResponse res) {
        logger.debug("handleRequest");
        ModelAndView mv = new ModelAndView();
        mv.addObject("weather", "cold");
        mv.setViewName("weather");
        mv.setStatus(HttpStatus.OK);
        logger.debug("mv model info: " + mv.getModelInfo());
        return mv;
    }
}
