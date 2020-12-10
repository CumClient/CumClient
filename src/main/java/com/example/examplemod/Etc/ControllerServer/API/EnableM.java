package com.example.examplemod.Etc.ControllerServer.API;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.ControllerServer.ServerHelper;
import com.example.examplemod.ExampleMod;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class EnableM implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String module = exchange.getRequestHeaders().getFirst("m");
        ModuleBase b = ExampleMod.MMN.getModuleByName(module);
        if (b == null) {
            ServerHelper.WriteToOutputStream(exchange,"Error");
            return;
        }
        b.setEnabled(true);
        ServerHelper.WriteToOutputStream(exchange,"done");
    }
}
