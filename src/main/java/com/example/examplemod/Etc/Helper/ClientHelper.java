package com.example.examplemod.Etc.Helper;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

public class ClientHelper {
    public static void SendClientNotif(String text) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(text), UUID.randomUUID());
    }
}
