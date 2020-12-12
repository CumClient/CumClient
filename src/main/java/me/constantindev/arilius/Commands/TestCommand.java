package me.constantindev.arilius.Commands;

import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
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
