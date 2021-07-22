package Handler;

import Requests.EventRequest;
import Responses.EventResponse;
import Serializer.JsonSerializer;
import Services.EventService;
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
                String[] args = uri.split("/");
                EventRequest request = null;
                EventService event = new EventService();
                EventResponse response = null;

                //TODO get authtokens?
                if(args.length < 2){
                request = new EventRequest();
                response = event.event(request);
                }
                else{
                request = new EventRequest(args[2]);
                response = event.allEvent(request);
                }

                String strResponse = "";

                OutputStream respBody = exchange.getResponseBody();
                strResponse = JsonSerializer.serialize(response);
                writeString(strResponse,respBody);
                respBody.close();

            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                exchange.getResponseBody().close();
            }
        } catch (IOException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
