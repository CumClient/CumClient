package me.constantindev.arilius.Etc.Events;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class BlockRenderEV {
    @SubscribeEvent
    public void RenderWorldLastEvent(net.minecraftforge.client.event.RenderWorldLastEvent e) {
        Minecraft.getInstance().player.world.loadedTileEntityList.forEach(tileEntity -> {
            if (tileEntity.getType().equals(TileEntityType.CHEST)) {
                Minecraft.getInstance().ingameGUI.blitBlackOutline(50,50,(integer, integer2) -> {

                });

            }
        });
    }
}
