package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

public class Scaffold extends ModuleBase {
    public Scaffold() {
        super("Scaffold", "Automatically places the current block selected under you");
    }

    @Override
    public void run() {
        BlockPos b = new BlockPos(Minecraft.getInstance().player.getPosX(), Minecraft.getInstance().player.getPosY() - 1, Minecraft.getInstance().player.getPosZ());
        Vector3d a = Minecraft.getInstance().player.getLookVec();
        Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerTryUseItemOnBlockPacket(Hand.MAIN_HAND, new BlockRayTraceResult(a, Direction.getFacingFromVector(a.x, a.y, a.z), b, false)));
        super.run();
    }
}
