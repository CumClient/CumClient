package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.CheatConfigKey;
import me.constantindev.arilius.Etc.ControllerServer.DataSet;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;
import org.lwjgl.system.CallbackI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetConfig implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<CheatConfigKey> a = Arilius.config.getAll();
        DataSet set = new DataSet();
        List<String> set1 = new ArrayList<>();
        a.forEach(cheatConfigKey -> {
            set1.add(cheatConfigKey.getKey()+"="+cheatConfigKey.getValue().toString());
        });
        set.append("config",String.join(";",set1));
        ServerHelper.WriteToOutputStream(exchange,set.format());
    }
}
