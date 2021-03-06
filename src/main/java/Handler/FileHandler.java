package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    //Handles localhost requests
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toUpperCase().equals("GET")){
                String urlPath = exchange.getRequestURI().toString();

                if(urlPath == null || urlPath.equals("/")){
                    urlPath = "/index.html";
                }

                String filePath = "web" + urlPath;
                File file = new File(filePath);

                String path404 = "web/HTML/404.html";

                if(file.exists()){
                  exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                }
                //If file doesn't exist, it returns a 404 error
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND,0);
                    file = new File(path404);
                }

                //Reads the file and writes it to the HttpExchange's output stream
                OutputStream respBody = exchange.getResponseBody();
                Files.copy(file.toPath(), respBody);
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
