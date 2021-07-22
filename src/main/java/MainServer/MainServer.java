package MainServer;

import Handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

public class MainServer {
    //Two primary functions
    //WebApis
    //Test application - The one given in Web

    private void startServer(int port) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress(port);
        HttpServer server = HttpServer.create(serverAddress, 10);
        registerHandlers(server);
        server.start();
        System.out.println("FamilyMapServer listening on port " + port);
    }

    private void registerHandlers(HttpServer server){
        server.createContext("/", new FileHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
//        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());

    }

    public static void main(String[] args) throws IOException {
        String portString = args[0];
        int port = Integer.parseInt(portString);

        new MainServer().startServer(port);
    }

}
