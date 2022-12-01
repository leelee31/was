package controller;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.ModelAndView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeController implements Controller {
    private final static Logger logger = LoggerFactory.getLogger(TimeController.class);

    @Override
    public ModelAndView handleRequest(HttpRequest req, HttpResponse res) {
        logger.debug("handleRequest");
        ModelAndView mv = new ModelAndView();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        mv.addObject("time", time.format(formatter));
        mv.setViewName("time");
        mv.setStatus(HttpStatus.OK);
        logger.debug("mv model info: " + mv.getModelInfo());
        return mv;
    }
}
