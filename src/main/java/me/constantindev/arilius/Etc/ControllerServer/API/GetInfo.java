package me.constantindev.arilius.Etc.ControllerServer.API;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.constantindev.arilius.Etc.ControllerServer.DataSet;
import me.constantindev.arilius.Etc.ControllerServer.ServerHelper;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class GetInfo implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        assert Minecraft.getInstance().player != null;
        String name = Minecraft.getInstance().player.getGameProfile().getName();
        String id = Minecraft.getInstance().player.getGameProfile().getId().toString();
        String isLegacy = Minecraft.getInstance().player.getGameProfile().isLegacy() ? "Yes" : "No";
        String response = new DataSet()
                .append("player_uname", name)
                .append("player_uuid", id)
                .append("player_isLegit", isLegacy)
                .format();
        ServerHelper.WriteToOutputStream(exchange, response);
    }
}
