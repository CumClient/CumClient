package me.constantindev.arilius.Etc.Events;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.constantindev.arilius.Arilius;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerInfoEV {
    int i = 0;
    float tick = 0;

    @SubscribeEvent
    public void RenderPlayerEvent(RenderPlayerEvent ev) {
        if (!Arilius.MMN.getModuleByName("playerinfo").isEnabled()) return;
        int i2 = "deadmau5".equals(ev.getPlayer().getName().getString()) ? -10 : 0;
        MatrixStack matrixStack = ev.getMatrixStack();

        matrixStack.push();
        matrixStack.translate(0.0D, ev.getEntity().getHeight() + 1.0F, 0.0D);
        matrixStack.rotate(Minecraft.getInstance().getRenderManager().getCameraOrientation());
        matrixStack.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = matrixStack.getLast().getMatrix();
        float f1 = Minecraft.getInstance().gameSettings.getTextBackgroundOpacity(0.25F);
        int j = (int) (f1 * 255.0F) << 24;
        String t = "HP: " + (Math.round(ev.getPlayer().getHealth())) + " / " + ev.getPlayer().getMaxHealth();
        String t2 = "Distance: " + ev.getPlayer().getDistance(Minecraft.getInstance().player) + " blocks";
        //t2 = ev.getLight()+"";
        FontRenderer fontrenderer = Minecraft.getInstance().fontRenderer;
        float f2 = (float) (-fontrenderer.getStringPropertyWidth(new StringTextComponent(t)) / 2);

        tick += 0.05;
        if (tick > 255) {
            i++;
            tick = 0;
        }
        if (i > 2) i = 0;
        int r, g, b;
        r = (int) (i == 0 ? tick : (i == 1 ? Math.abs(tick - 255) : 0));
        g = (int) (i == 1 ? tick : (i == 2 ? Math.abs(tick - 255) : 0));
        b = (int) (i == 2 ? tick : (i == 0 ? Math.abs(tick - 255) : 0));
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;

        fontrenderer.func_243247_a(new StringTextComponent(t), f2, (float) i2, rgb, false, matrix4f, Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), false, j, 0x00FFFFFF);
        matrixStack.translate(0.0D, 10.0F, 0.0D);
        fontrenderer.func_243247_a(new StringTextComponent(t2), f2, (float) i2, rgb, false, matrix4f, Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), false, j, 0x00FFFFFF);
        matrixStack.pop();


        //GL11.glBegin( GL11.GL_TRIANGLE_FAN );
        //GL11.glEnd();

    }
}
