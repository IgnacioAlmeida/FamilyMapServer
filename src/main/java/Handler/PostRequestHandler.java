package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PostRequestHandler extends RequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

    }
    /*
    Parent for handlers that handle post requests
    Read the request Json and convert to a Java object

     */
}
