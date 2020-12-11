package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Etc.Base.ModuleBase;
import net.minecraft.client.Minecraft;

public class AutoWalk extends ModuleBase {
    public AutoWalk() {
        super("AutoWalk", "Automatically walks 4 u");
    }

    @Override
    public void run() {
        Minecraft.getInstance().gameSettings.keyBindForward.setPressed(true);
        super.run();
    }
}
