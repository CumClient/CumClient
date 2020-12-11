package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Main implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String resp = "https://cumclient.github.io/";
        exchange.sendResponseHeaders(302, resp.length());
        OutputStream a = exchange.getResponseBody();
        a.write(resp.getBytes());
        a.flush();
        a.close();
    }
}
