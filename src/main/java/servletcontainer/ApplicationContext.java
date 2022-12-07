package servletcontainer;

import servlet.handler.HandlerAdapter;
import servlet.handler.HandlerMapping;
import servlet.handler.HttpHandlerAdapter;
import servlet.handler.UrlControllerHandlerMapping;

public class ApplicationContext {
    private final HandlerMapping hm;
    private final HandlerAdapter ha;

    private ApplicationContext() {
        hm = new UrlControllerHandlerMapping();
        ha = new HttpHandlerAdapter();
        createUrlMatchDirectory();
    }

    private static class InnerApplicationContext {
        private static final ApplicationContext ac = new ApplicationContext();
    }

    public static ApplicationContext getApplicationContext() {
        return InnerApplicationContext.ac;
    }

    public HandlerMapping getHandlerMapping() {
        return hm;
    }

    public HandlerAdapter getHandlerAdapter() {
        return ha;
    }

    private void createUrlMatchDirectory() {
        hm.addHandlerMapping("/", "controller.HelloController");
        hm.addHandlerMapping("/hello", "controller.HelloController");
        hm.addHandlerMapping("/time", "controller.TimeController");
    }
}