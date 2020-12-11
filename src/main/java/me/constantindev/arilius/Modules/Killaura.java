package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.CheatConfigKey;
import me.constantindev.arilius.Etc.CheatConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;

public class Killaura extends ModuleBase {
    public Killaura() {
        super("Killaura", "Kills. A lot.");
    }

    int rad = 6;
    int radDiv = rad / 2;
    int tick = 0;

    @Override
    public void run() {
        tick++;
        if (tick > 400) {
            tick = 0;
            attack();
        }

        super.run();
    }

    @Override
    public void register(CheatConfigManager config) {

        config.addToConfig(new CheatConfigKey("range", 6, this));
        super.register(config);
    }

    void attack() {
        this.rad = Integer.parseInt(Arilius.config.getByName("range", this).getValue().toString());
        this.radDiv = rad / 2;
        try {
            Minecraft.getInstance().player.getEntityWorld().getEntitiesInAABBexcluding(Minecraft.getInstance().player, new AxisAlignedBB(
                    Minecraft.getInstance().player.getPosX() - radDiv,
                    Minecraft.getInstance().player.getPosY() - radDiv,
                    Minecraft.getInstance().player.getPosZ() - radDiv,
                    Minecraft.getInstance().player.getPosX() + radDiv,
                    Minecraft.getInstance().player.getPosY() + radDiv,
                    Minecraft.getInstance().player.getPosZ() + radDiv
            ), null).forEach(entity -> {
                if (entity.canBeAttackedWithItem())
                    Minecraft.getInstance().playerController.attackEntity(Minecraft.getInstance().player, entity);
            });
        } catch (Exception ex) {
        }
    }
}
