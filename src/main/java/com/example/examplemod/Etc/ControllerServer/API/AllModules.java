package com.example.examplemod.Etc.ControllerServer.API;

import com.example.examplemod.Etc.ControllerServer.DataSet;
import com.example.examplemod.Etc.ControllerServer.ServerHelper;
import com.example.examplemod.ExampleMod;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllModules implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<String> moduleN = new ArrayList<>();
        ExampleMod.MMN.get().forEach(moduleBase -> {
            moduleN.add(moduleBase.getName());
        });
        DataSet ds = new DataSet()
                .append("modules", String.join(";",moduleN));
        ServerHelper.WriteToOutputStream(exchange,ds.format());
    }
}
