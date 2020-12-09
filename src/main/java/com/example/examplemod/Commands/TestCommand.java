package com.example.examplemod.Commands;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockRayTraceResult;

import java.util.List;

public class TestCommand extends CommandBase {
    public TestCommand() {
        super("Test", "Bruh", new String[]{"test", "bruh", "lmao"});
    }

    @Override
    public void run(List<String> args) {
        ClientHelper.SelectFittingTool(((BlockRayTraceResult) Minecraft.getInstance().objectMouseOver).getPos());
        super.run(args);
    }
}
