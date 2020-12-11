package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.ControllerServer.DataSet;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllModules implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<String> moduleN = new ArrayList<>();
        Arilius.MMN.get().forEach(moduleBase -> {
            moduleN.add(moduleBase.getName());
        });
        DataSet ds = new DataSet()
                .append("modules", String.join(";", moduleN));
        ServerHelper.WriteToOutputStream(exchange, ds.format());
    }
}
