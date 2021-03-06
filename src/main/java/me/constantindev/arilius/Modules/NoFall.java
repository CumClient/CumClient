package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Etc.Base.ModuleBase;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerPacket;

public class NoFall extends ModuleBase {
    public NoFall() {
        super("NoFall", "Prevents fall damage from bothering you");
    }

    @Override
    public void run() {
        if (!Minecraft.getInstance().isGamePaused()) Minecraft.getInstance().player.addVelocity(0, 0.001, 0);
        Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerPacket(true));

        Minecraft.getInstance().player.velocityChanged = true;
        super.run();
    }
}
