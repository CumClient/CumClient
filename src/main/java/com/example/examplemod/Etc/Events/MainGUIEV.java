package com.example.examplemod.Etc.Events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.GUI.MainMenuGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MainGUIEV {
    @SubscribeEvent
    public void TickEvent(net.minecraftforge.event.TickEvent ev) {
        ExampleMod.tick++;
        if (ExampleMod.tick > 255) ExampleMod.tick = 0;
        ExampleMod.MMN.get().forEach(moduleBase -> {
            if (moduleBase.isEnabled()) try {
                moduleBase.run();
            } catch (Exception ex) {
            }
        });
        if (Minecraft.getInstance().currentScreen instanceof MainMenuScreen) {
            Minecraft.getInstance().displayGuiScreen(new MainMenuGUI());
        }
    }
}
