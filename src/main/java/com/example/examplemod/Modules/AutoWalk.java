package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import net.minecraft.client.Minecraft;

public class AutoWalk extends ModuleBase {
    public AutoWalk() {
        super("AutoWalk","Automatically walks 4 u");
    }

    @Override
    public void run() {
        Minecraft.getInstance().gameSettings.keyBindForward.setPressed(true);
        super.run();
    }
}
