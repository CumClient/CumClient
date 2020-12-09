package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class Tunnel extends ModuleBase {
    public Tunnel() {
        super("Tunnel", "Makes a tunnel just for you");
    }

    int r(double i, int v) {
        return (int) (Math.round(i / v) * v);
    }

    @Override
    public void onEnable() {


        super.onEnable();
    }

    @Override
    public void run() {
        double x1 = Minecraft.getInstance().player.getPosX();
        double z = Minecraft.getInstance().player.getPosZ();
        Vector2f c = Minecraft.getInstance().player.getPitchYaw();

        double pitch = c.y;
        if (Minecraft.getInstance().gameSettings.keyBindRight.isPressed()) pitch += 90;
        if (Minecraft.getInstance().gameSettings.keyBindLeft.isPressed()) pitch -= 90;

        Minecraft.getInstance().player.setPositionAndRotation(x1, Minecraft.getInstance().player.getPosY(), z, r((int) pitch, 90), 0);
        Minecraft.getInstance().gameSettings.keyBindForward.setPressed(true);
        Vector3d a = Minecraft.getInstance().player.getLookVec();
        BlockPos p = Minecraft.getInstance().player.getPosition();

        for (int x = -1; x < 2; x++) {


            Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, p, Direction.UP));
            Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.STOP_DESTROY_BLOCK, p, Direction.UP));
            Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, p.add(0, 1, 0), Direction.UP));
            Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.STOP_DESTROY_BLOCK, p.add(0, 1, 0), Direction.UP));
            p = p.add(a.x, a.y, a.z);
        }

        super.run();
    }
}
