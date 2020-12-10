package com.example.examplemod.Etc.ControllerServer.API;

import com.example.examplemod.Etc.ControllerServer.DataSet;
import com.example.examplemod.Etc.ControllerServer.ServerHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.client.Minecraft;
import org.lwjgl.system.CallbackI;

import java.io.IOException;

public class Info implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String cs = Minecraft.getInstance().currentScreen.toString();
        DataSet a = new DataSet().append("currentscreen",cs);
        ServerHelper.WriteToOutputStream(exchange,a.format());
    }
}
