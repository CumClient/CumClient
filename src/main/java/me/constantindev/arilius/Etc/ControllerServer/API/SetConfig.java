package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;

import java.io.IOException;

public class SetConfig implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String k = exchange.getRequestHeaders().getFirst("key");
        String v = exchange.getRequestHeaders().getFirst("value");
        String o = exchange.getRequestHeaders().getFirst("module");
        if (k == null || v == null || o == null) {
            ServerHelper.WriteToOutputStream(exchange,"Invalid request bruh moment 1");
            return;
        }
        ModuleBase a = Arilius.MMN.getModuleByName(o);
        if (a == null) {
            ServerHelper.WriteToOutputStream(exchange,"Invalid request bruh moment");
            return;
        }
        Arilius.config.getByOrigin(a).forEach(cheatConfigKey -> {
            if (cheatConfigKey.getKey().equalsIgnoreCase(k)) cheatConfigKey.setValue(v);
        });
        ServerHelper.WriteToOutputStream(exchange,"Done");
    }
}
