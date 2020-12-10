package com.example.examplemod.Etc.ControllerServer;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ServerHelper {
    public static void WriteToOutputStream(HttpExchange c, String data) throws IOException {
        c.getResponseHeaders().add("Access-Control-Allow-Origin","*");
        c.getResponseHeaders().add("Access-Control-Allow-Headers","*");
        c.sendResponseHeaders(200,data.length());

        OutputStream os = c.getResponseBody();
        os.write(data.getBytes());
        os.flush();
        os.close();
    }
}
