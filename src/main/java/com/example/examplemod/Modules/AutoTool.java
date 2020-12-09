package com.example.examplemod.Modules;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
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
