package controller;

import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.ModelAndView;

public class HelloController implements Controller {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Override
    public ModelAndView handleRequest(HttpRequest req, HttpResponse res) {
        logger.debug("handleRequest");
        ModelAndView mv = new ModelAndView();
        mv.addObject("text", "hellooooooooooo!!");
        mv.setViewName("hello");
        mv.setStatus(HttpStatus.OK);
        logger.debug(mv.getModelInfo());
        return mv;
    }
}
