package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.CheatConfigKey;
import com.example.examplemod.Etc.CheatConfigManager;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.ExampleMod;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class Nuker extends ModuleBase {
    public Nuker() {
        super("Nuker", "brrrrr");
    }

    int rad = 6;
    int radD = rad / 2;
    int tick = 0;

    @Override
    public void register(CheatConfigManager config) {
        config.addToConfig(new CheatConfigKey("range", 6, this));
        super.register(config);
    }

    @Override
    public void run() {
        tick++;
        if (tick > 20) tick = 0;
        else return;
        this.rad = Integer.parseInt(ExampleMod.config.getByName("range", this).getValue().toString());
        this.radD = this.rad / 2;
        double x = Minecraft.getInstance().player.getPosX();
        double y = Minecraft.getInstance().player.getPosY();
        double z = Minecraft.getInstance().player.getPosZ();
        for (int cx = -radD; cx < radD; cx++) {
            final double cxf = cx + x;
            for (int cy = -radD; cy < radD; cy++) {
                final double cyf = cy + y;
                for (int cz = -radD; cz < radD; cz++) {

                    final double czf = cz + z;
                    BlockPos current = new BlockPos(cxf, cyf, czf);
                    if (current.equals(Minecraft.getInstance().player.getPosition().add(0, -1, 0)))
                        continue;
                    if (Minecraft.getInstance().player.world.getBlockState(current).getMaterial().equals(Material.AIR))
                        continue;
                    ClientHelper.SelectFittingTool(current);
                    Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, current, Direction.UP));
                    Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.STOP_DESTROY_BLOCK, current, Direction.UP));
                    //return;
                }
            }
        }
        Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK, Minecraft.getInstance().player.getPosition().add(0, -1, 0), Direction.UP));
        Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.STOP_DESTROY_BLOCK, Minecraft.getInstance().player.getPosition().add(0, -1, 0), Direction.UP));
        super.run();
    }
}
