package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.CheatConfigKey;
import com.example.examplemod.Etc.CheatConfigManager;
import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.time.chrono.MinguoEra;

public class Nuker extends ModuleBase {
    public Nuker() {
        super("Nuker","brrrrr");
    }
    int rad = 6;
    int radD = rad/2;

    @Override
    public void register(CheatConfigManager config) {
        config.addToConfig(new CheatConfigKey("range",6,this));
        super.register(config);
    }

    @Override
    public void run() {
        this.rad = Integer.parseInt(ExampleMod.config.getByName("range",this).getValue().toString());
        this.radD = this.rad / 2;
        double x = Minecraft.getInstance().player.getPosX();
        double y = Minecraft.getInstance().player.getPosY();
        double z = Minecraft.getInstance().player.getPosZ();
        for(int cx = -radD; cx < radD; cx++) {
            final double cxf = cx+x;
            for(int cy = -radD; cy < radD; cy++) {
                final double cyf = cy+y;
                for(int cz = -radD; cz < radD; cz++) {
                    final double czf = cz+z;
                    Minecraft.getInstance().player.world.sendPacketToServer(new CPlayerDiggingPacket(CPlayerDiggingPacket.Action.START_DESTROY_BLOCK,new BlockPos(cxf,cyf,czf), Direction.UP));
                }
            }
        }
        super.run();
    }
}
