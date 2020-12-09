package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.CheatConfigKey;
import com.example.examplemod.Etc.CheatConfigManager;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;

public class Fly extends ModuleBase {
    public Fly() {
        super("Fly", "Weeeee");
    }

    @Override
    public void register(CheatConfigManager config) {
        config.addToConfig(new CheatConfigKey<>("mode", "jetpack", this));
        config.addToConfig(new CheatConfigKey<>("strength", 0.005, this));
        super.register(config);
    }

    @Override
    public void run() {

        switch (ExampleMod.config.getByName("mode", this).getValue().toString().toLowerCase()) {

            case "jetpack":
                if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown()) {
                    double strength = 0.005;
                    try {
                        strength = Double.parseDouble(ExampleMod.config.getByName("strength", this).getValue().toString());
                    } catch (Exception ex) {
                        ExampleMod.config.getByName("strength", this).setValue(strength);
                    }
                    Minecraft.getInstance().player.addVelocity(0, strength, 0);
                    Minecraft.getInstance().player.velocityChanged = true;
                }
                break;
            case "vanilla":
                Minecraft.getInstance().player.abilities.isFlying = true;
                break;
            case "tp":
                double x = 0, z = 0;
                if (Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown()) {
                    Vector3d v = Minecraft.getInstance().player.getLookVec();
                    x = v.getX() * 2;
                    z = v.getZ() * 2;
                    Minecraft.getInstance().player.velocityChanged = true;
                }
                Minecraft.getInstance().player.setMotion(x, Minecraft.getInstance().player.getLookVec().getY(), z);

                break;
            default:
                ExampleMod.config.getByName("mode", this).setValue("jetpack");
                ClientHelper.SendClientNotif("[FLY] Invalid mode detected, switching to jetpack. Please choose one of vanilla or jetpack.");
        }
        super.run();
    }
}
