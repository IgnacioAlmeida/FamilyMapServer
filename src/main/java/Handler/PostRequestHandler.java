package Handler;

import Responses.Response;
import Serializer.JsonSerializer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Parent class that post data to the database.
 * Classes that post data to the database are: Load, Register, Login, Fill, and CLear.
 * It reads the request Json and converts it to a Java Object
 */
public class PostRequestHandler extends RequestHandler{

    public void postRequests(Response response, HttpExchange exchange) throws IOException {
        if(response.isSuccess()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
        }

        OutputStream os = exchange.getResponseBody();
        String serialized = JsonSerializer.serialize(response);
        writeString(serialized,os);
        os.close();
    }

}
