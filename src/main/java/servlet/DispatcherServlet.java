package servlet;

import controller.Controller;
import servlet.handler.HandlerAdapter;
import servlet.handler.HandlerMapping;
import http.HttpRequest;
import http.HttpResponse;
import servletcontainer.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

public class DispatcherServlet implements Servlet {
    private HandlerMapping hm;
    private HandlerAdapter ha;

    private DispatcherServlet() {}

    private static class InnerDispatcherServlet {
        private static final DispatcherServlet ds = new DispatcherServlet();
    }

    @Override
    public void init() {}

    @Override
    public void service(HttpRequest req, HttpResponse res) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        doDispatch(req, res);
    }

    @Override
    public void destroy() {}

    public static Servlet getDispatcherServlet(ApplicationContext ac) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        InnerDispatcherServlet.ds.hm = ac.getHandlerMapping();
        InnerDispatcherServlet.ds.ha = ac.getHandlerAdapter();
        return InnerDispatcherServlet.ds;
    }

    private void doDispatch(HttpRequest req, HttpResponse res) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        Controller handler = hm.getHandler(req);
        ModelAndView mv = ha.handle(req, res, handler);
        resolveView(mv);
        res.setBody(mv.getView());
        res.setStatus(mv.getStatus());
    }

    private void resolveView(ModelAndView mv) throws IOException {
        String prefix = "./webapp/";
        String suffix = ".html";
        byte[] body = Files.readAllBytes(new File(prefix + mv.getViewName() + suffix).toPath());
        mv.setView(body);
    }
}