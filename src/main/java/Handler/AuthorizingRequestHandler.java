package Handler;

import Responses.Response;
import Serializer.JsonSerializer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class AuthorizingRequestHandler extends RequestHandler {
    /**
     * Parent for handlers that require authroization
     * Read the authorization header to get the auth_token. Return a 401 if missing or invalid
     */
//  public void authorizationRequest(Response response, HttpExchange exchange, int argsLength){
//      if(args.length > 2){
//          response = event.event(authToken, args[2]);
//
//          if(response.isSuccess()) {
//              exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//          }
//          else{
//              exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//          }
//          OutputStream respBody = exchange.getResponseBody();
//          strResponse = JsonSerializer.serialize(response);
//          writeString(strResponse, respBody);
//          respBody.close();
//      }
//      else{
//          listResponse = event.allEvents(authToken);
//
//          if(listResponse.isSuccess()) {
//              exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//          }
//          else{
//              exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//          }
//          OutputStream respBody = exchange.getResponseBody();
//          strResponse = JsonSerializer.serialize(listResponse);
//          writeString(strResponse, respBody);
//          respBody.close();
//      }
//  }

}
