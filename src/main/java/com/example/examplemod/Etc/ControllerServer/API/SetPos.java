package com.example.examplemod.Etc.ControllerServer.API;

import com.example.examplemod.Etc.ControllerServer.ServerHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class SetPos implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ServerHelper.WriteToOutputStream(exchange,"done");
        String pos = exchange.getRequestHeaders().getFirst("pos");
        if (pos == null) return;

        String[] posa = pos.split(",");
        if (posa.length != 3) return;
        int x = Integer.parseInt(posa[0]);
        int y = Integer.parseInt(posa[1]);
        int z = Integer.parseInt(posa[2]);
        Minecraft.getInstance().player.setPosition(x,y,z);
    }
}
