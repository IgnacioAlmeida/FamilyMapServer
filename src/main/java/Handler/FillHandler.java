package Handler;

import DataAccess.DataAccessException;
import Requests.FillRequest;
import Responses.Response;
import Serializer.JsonSerializer;
import Services.FillService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class FillHandler extends PostRequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("POST")){
                String uri = exchange.getRequestURI().toString();
                String[] args = uri.split("/");
                FillRequest request = null;
                if(args.length < 4){
                    request = new FillRequest(args[2],4);
                }
                else{
                    request = new FillRequest(args[2], Integer.parseInt(args[3]));
                }
                FillService fill = new FillService();
                Response response = fill.fill(request);

                postRequests(response,exchange);
            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
