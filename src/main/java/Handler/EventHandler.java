package Handler;

import DataAccess.DataAccessException;
import Responses.EventResponse;
import Responses.EventsListResponse;
import Serializer.JsonSerializer;
import Services.EventService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class EventHandler extends RequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("GET")){
                String uri = exchange.getRequestURI().toString();
                Headers auth = exchange.getRequestHeaders();
                String[] args = uri.split("/");
                EventService event = new EventService();
                EventResponse response = null;
                EventsListResponse listResponse = null;
                String strResponse = "";
                String authToken = "";

                if(auth.containsKey("Authorization")){
                    authToken = auth.getFirst("Authorization");
                }

                if(args.length > 1){
                    response = event.event(authToken, args[2]);

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                    OutputStream respBody = exchange.getResponseBody();
                    strResponse = JsonSerializer.serialize(response);
                    writeString(strResponse,respBody);
                    respBody.close();
                }
                else{
                    listResponse = event.allEvents(authToken);

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                    OutputStream respBody = exchange.getResponseBody();
                    strResponse = JsonSerializer.serialize(listResponse);
                    writeString(strResponse,respBody);
                    respBody.close();
                }

            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                exchange.getResponseBody().close();
            }
        } catch (IOException | DataAccessException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
