package servletcontainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
    private static final Logger logger = LoggerFactory.getLogger(WebServer.class);
    private static final int DEFAULT_PORT = 8080;
    private static final int NUM_THREADS = 5;

    public void start() {
         ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            while (true) {
                logger.debug("waiting connect.. ");
                Socket connection = serverSocket.accept();
                logger.debug("accept!");
                Runnable r = new RequestHandler(connection);
                executor.submit(r);
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
