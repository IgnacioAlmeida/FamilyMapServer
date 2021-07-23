package Handler;

import DataAccess.DataAccessException;
import Requests.LoadRequest;
import Responses.Response;
import Serializer.JsonSerializer;
import Services.LoadService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class LoadHandler extends PostRequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if (exchange.getRequestMethod().toUpperCase().equals("POST")){
                InputStream is = exchange.getRequestBody();
                String requestData = readString(is);
                LoadRequest request = JsonSerializer.deserialize(requestData, LoadRequest.class);
                LoadService load = new LoadService();
                Response response = load.load(request);

                postRequests(response, exchange);

            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
