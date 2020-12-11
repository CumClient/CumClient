package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Etc.ControllerServer.DataSet;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class Info implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String cs = Minecraft.getInstance().currentScreen.toString();
        DataSet a = new DataSet().append("currentscreen", cs);
        ServerHelper.WriteToOutputStream(exchange, a.format());
    }
}
