package com.example.examplemod.Etc.ControllerServer.API;

import com.example.examplemod.Etc.ControllerServer.DataSet;
import com.example.examplemod.Etc.ControllerServer.ServerHelper;
import com.example.examplemod.ExampleMod;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EnabledModules implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DataSet resp = new DataSet();
        AtomicReference<String> s = new AtomicReference<>("");
        ExampleMod.MMN.get().forEach(moduleBase -> {
            if (moduleBase.isEnabled()) s.set(s.get()+";"+moduleBase.getName());
        });
        resp.append("modules",s.get());
        ServerHelper.WriteToOutputStream(exchange,resp.format());
    }
}
