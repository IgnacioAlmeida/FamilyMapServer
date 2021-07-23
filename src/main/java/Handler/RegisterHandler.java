package Handler;

import DataAccess.DataAccessException;
import Requests.RegisterRequest;
import Responses.RegisterResponse;
import Serializer.JsonSerializer;
import Services.RegisterService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

//Reads Jason and use Gson to create a register request object
//then creates an instance of register service and calls its register method
//It will then work in services models and dao
//and then it will get that result and convert it from gson to json to write it back out to output screen
public class RegisterHandler extends PostRequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("POST")){
                InputStream is = exchange.getRequestBody();
                String requestData = readString(is);
                RegisterRequest request = JsonSerializer.deserialize(requestData, RegisterRequest.class);
                RegisterService register = new RegisterService();
                RegisterResponse response = register.register(request);

                postRequests(response,exchange);
            }

        } catch (IOException | DataAccessException e){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                exchange.getResponseBody().close();
                e.printStackTrace();
        }
    }




}
