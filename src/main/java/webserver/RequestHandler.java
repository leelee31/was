package webserver;

import http.HttpRequest;
import http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.*;
import util.HttpUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        logger.debug("Connection ip: {}, port: {} ", connection.getInetAddress(), connection.getPort());
        try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            DataOutputStream dos = new DataOutputStream(out)) {

            HttpRequest req = HttpUtil.parseRequest(in);
            HttpResponse res = new HttpResponse();

            ApplicationContext ac = ApplicationContext.getApplicationContext();
            Servlet servlet = DispatcherServlet.getDispatcherServlet(ac);
            servlet.service(req, res);

            byte[] body = res.getBody();
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}