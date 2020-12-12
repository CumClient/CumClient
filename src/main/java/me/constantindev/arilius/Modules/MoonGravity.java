package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import net.minecraft.client.Minecraft;

public class MoonGravity extends ModuleBase {
    public MoonGravity() {
        super("MoonGravity","Makes you go weeee when you jump");
    }

    @Override
    public void run() {
        if (Minecraft.getInstance().isGamePaused()) return;
        double strength = Arilius.MMN.getModuleByName("NoFall").isEnabled()?0.0006:0.0016;
        Minecraft.getInstance().player.addVelocity(0,strength,0);
        Minecraft.getInstance().player.velocityChanged = true;
        super.run();
    }
}
