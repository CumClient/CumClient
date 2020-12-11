package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;

import java.io.IOException;

public class EnableM implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String module = exchange.getRequestHeaders().getFirst("m");
        ModuleBase b = Arilius.MMN.getModuleByName(module);
        if (b == null) {
            ServerHelper.WriteToOutputStream(exchange, "Error");
            return;
        }
        b.setEnabled(true);
        ServerHelper.WriteToOutputStream(exchange, "done");
    }
}
