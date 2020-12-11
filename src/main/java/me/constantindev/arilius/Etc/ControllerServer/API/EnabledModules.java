package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.ControllerServer.DataSet;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class EnabledModules implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DataSet resp = new DataSet();
        AtomicReference<String> s = new AtomicReference<>("");
        Arilius.MMN.get().forEach(moduleBase -> {
            if (moduleBase.isEnabled()) s.set(s.get() + ";" + moduleBase.getName());
        });
        resp.append("modules", s.get());
        ServerHelper.WriteToOutputStream(exchange, resp.format());
    }
}
