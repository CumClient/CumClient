package com.example.examplemod.Etc.Helper;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

public class ClientHelper {
    public static void SendClientNotif(String text) {
        SendClientNotif(new StringTextComponent(text));
    }

    public static void SendClientNotif(ITextComponent comp) {
        Minecraft.getInstance().player.sendMessage(comp, UUID.randomUUID());
    }

    public static void SelectFittingTool(BlockPos b) {
        if (Minecraft.getInstance().player == null) return;
        BlockState ev = Minecraft.getInstance().player.world.getBlockState(b);

        PlayerInventory e = Minecraft.getInstance().player.inventory;
        float bestSpeed = 1f;
        int bsl = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack s = e.getStackInSlot(i);
            float speed = s.getDestroySpeed(ev);
            if (speed > bestSpeed) bsl = i;
        }
        if (bsl != -1) e.setPickedItemStack(e.getStackInSlot(bsl));
    }
}
