package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.CheatConfigKey;
import me.constantindev.arilius.Etc.CheatConfigManager;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidAttributes;

public class Jesus extends ModuleBase {
    public Jesus() {
        super("Jesus","Makes you unable to sink in water");
    }

    @Override
    public void register(CheatConfigManager config) {
        config.addToConfig(new CheatConfigKey<>("mode","dip",this));
        super.register(config);
    }

    @Override
    public void run() {
        switch(Arilius.config.getByName("mode",this).getValue().toString().toLowerCase()) {
            case "dip":
                if (Minecraft.getInstance().player.isInWater()) {
                    Minecraft.getInstance().player.addVelocity(0,0.0012,0);
                    Minecraft.getInstance().player.velocityChanged = true;
                }
                break;
            case "dontfall":
                BlockPos e = Minecraft.getInstance().player.getPosition();
                if (Minecraft.getInstance().player.world.getBlockState(e.down()).getFluidState().getLevel() != 0) {
                    double addition = 0;
                    if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown()) {
                        addition = 0.1;
                    }
                    Minecraft.getInstance().player.setMotion(Minecraft.getInstance().player.getMotion().add(0,(-Minecraft.getInstance().player.getMotion().y),0).add(0,addition,0));
                }
                break;
            case "teleport":
                if (Minecraft.getInstance().player.isInWater()) {
                    Minecraft.getInstance().player.setPosition(Minecraft.getInstance().player.getPosX(),Minecraft.getInstance().player.getPosY()+0.015,Minecraft.getInstance().player.getPosZ());
                }

                break;
            default:
                ClientHelper.SendClientNotif("[JESUS] Invalid mode. Reverting back to mode \"dip\". Please choose one of teleport, dontfall and dip.");
                Arilius.config.getByName("mode", this).setValue("dip");
        }
        super.run();
    }
}
