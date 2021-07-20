package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

import java.io.IOException;
import java.net.HttpURLConnection;

public class AuthorizingRequestHandler extends RequestHandler implements HttpHandler {
    /**
     * Parent for handlers that require authroization
     * Read the authorization header to get the auth_token. Return a 401 if missing or invalid
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("GET")){
                if(exchange.getRequestHeaders().containsKey("Authorization")){
                    String authToken = exchange.getRequestHeaders().getFirst("Authorization");
                    //send it to the service to check
                    //check if it is in the database and find the username - then compare than username with the associated username
                }
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED,0);
                }
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            }
        } catch (IOException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

}
