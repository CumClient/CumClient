package com.example.examplemod.Etc.Events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.GUI.MainMenuGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MainGUIEV {
    @SubscribeEvent
    public void TickEvent(net.minecraftforge.event.TickEvent ev) {
        ExampleMod.MMN.get().forEach(moduleBase -> {
            if (moduleBase.isEnabled()) moduleBase.run();
        });
        if (Minecraft.getInstance().currentScreen instanceof MainMenuScreen) {
            Minecraft.getInstance().displayGuiScreen(new MainMenuGUI());
        }
    }
}
