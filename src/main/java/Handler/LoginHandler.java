package Handler;

import DataAccess.DataAccessException;
import Requests.LoadRequest;
import Requests.LoginRequest;
import Responses.LoginResponse;
import Serializer.JsonSerializer;
import Services.LoadService;
import Services.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class LoginHandler extends PostRequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if (exchange.getRequestMethod().toUpperCase().equals("POST")){
                InputStream is = exchange.getRequestBody();
                String requestData = readString(is);
                LoginRequest request = JsonSerializer.deserialize(requestData, LoginRequest.class);
                LoginService login = new LoginService();
                LoginResponse response = login.login(request);

                postRequests(response, exchange);

            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
