package Handler;

import DataAccess.DataAccessException;
import Responses.EventResponse;
import Responses.EventsListResponse;
import Responses.PersonListResponse;
import Responses.PersonResponse;
import Serializer.JsonSerializer;
import Services.EventService;
import Services.PersonService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler extends PostRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("GET")){
                String uri = exchange.getRequestURI().toString();
                Headers auth = exchange.getRequestHeaders();
                String[] args = uri.split("/");
                PersonService service = new PersonService();
                PersonResponse response = null;
                PersonListResponse listResponse = null;
                String strResponse = "";
                String authToken = "";

                if(auth.containsKey("Authorization")){
                    authToken = auth.getFirst("Authorization");
                }

                if(args.length > 2){
                    response = service.person(authToken, args[2]);

                    postRequests(response, exchange);
                }
                else{
                    listResponse = service.allPersons(authToken);

                    postRequests(listResponse,exchange);
                }

            }
        } catch (IOException | DataAccessException e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
