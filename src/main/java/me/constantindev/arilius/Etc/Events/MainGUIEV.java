package me.constantindev.arilius.Etc.Events;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.GUI.MainMenuGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MainGUIEV {
    @SubscribeEvent
    public void TickEvent(net.minecraftforge.event.TickEvent ev) {

        Arilius.bindings.forEach((integer, moduleBase) -> {
            if (Minecraft.getInstance().currentScreen != null) return;
            if (integer.isPressed()) {
                moduleBase.setEnabled(!moduleBase.isEnabled());
                //ClientHelper.SendClientNotif("A");
            }
        });
        Arilius.tick++;
        if (Arilius.tick > 255) {
            Arilius.tick = 0;
        }
        Arilius.MMN.get().forEach(moduleBase -> {
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
