package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockRayTraceResult;

public class AutoTool extends ModuleBase {
    public AutoTool() {
        super("AutoTool", "Automatically selects the best tool");
    }

    int tick = 0;

    @Override
    public void run() {
        tick++;

        if (tick > 20) {
            tick = 0;
            ClientHelper.SelectFittingTool(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos());
        }

        super.run();
    }
}
