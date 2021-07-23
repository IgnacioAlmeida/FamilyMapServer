package Handler;

import DataAccess.DataAccessException;
import Responses.Response;
import Services.ClearService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ClearHandler extends PostRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try{
            if (exchange.getRequestMethod().toUpperCase().equals("POST")){
                ClearService clear = new ClearService();
                Response response = clear.clear();

                postRequests(response, exchange);

            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

}
