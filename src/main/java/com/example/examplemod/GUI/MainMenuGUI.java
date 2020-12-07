package com.example.examplemod.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.opengl.GL11;

public class MainMenuGUI extends Screen {
    public MainMenuGUI() {
        super(new StringTextComponent("Main menu"));
    }

    String[] quotes = new String[]{"this client is a meme", "im boutta fucking nut", "why", "explain life", "what is the reason of life again?", "i spent 5 weeks making this", "this is my first cheat client", "please kill me", "what am i doing with my life"};
    String cq = "\"" + quotes[(int) Math.floor(Math.random() * quotes.length)] + "\"";

    @Override
    protected void init() {
        super.init();
    }

    int tick = 0;
    int i = 0;
    double mtp = 1;
    boolean mtpI = false;

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (mtpI) {
            mtp += 0.05;
        }

        if (mtp > 5.0) {
            mtpI = false;
        }
        if (tick == 20 && mtp != 1.0 && !mtpI) mtp = 1.0;
        tick++;
        if (tick > 255) {
            i++;
            tick = 0;
        }
        if (i > 2) i = 0;

        int r, g, b;
        r = i == 0 ? tick : (i == 1 ? Math.abs(tick - 255) : 0);
        g = i == 1 ? tick : (i == 2 ? Math.abs(tick - 255) : 0);
        b = i == 2 ? tick : (i == 0 ? Math.abs(tick - 255) : 0);
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        RenderSystem.enableBlend();
        matrixStack.push();
        Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation("cumclient", "bg.png"));
        blit(matrixStack, 0, 0, getBlitOffset(), this.width, this.height, this.width, this.height, this.height, this.width);
        ImageButton imgb = new net.minecraft.client.gui.widget.button.ImageButton((this.width / 2) - (this.font.getStringWidth("NUT") * 7 / 2), 50, this.font.getStringWidth("NUT") * 7, 12, 0, 0, 0, new ResourceLocation("cumclient", "btnbg.png"), (button) -> {
            mtpI = true;
        });
        this.addButton(imgb);
        GL11.glScalef((float) (8 * mtp), 1, 1);
        this.font.drawString(matrixStack, "NUT", (float) ((this.width / 2 - (this.font.getStringWidth("NUT") / 2 * (8 * mtp - 1))) / (8 * mtp)), 50, rgb);
        GL11.glScalef((float) (0.125f / mtp), 1, 1);
        this.font.drawString(matrixStack, "CLIENT", this.width / 2 - (this.font.getStringWidth("CLIENT") / 2), 50 + 11, rgb);

        this.font.drawString(matrixStack, cq, this.width / 2 - (this.font.getStringWidth(cq) / 2), 50 + 11 * 2, rgb);
        this.addButton(new Button(this.width / 2 - (150 / 2), this.height / 2 - 10, 150, 20, new StringTextComponent("Singleplayer"), (button) -> {
            Minecraft.getInstance().displayGuiScreen(new net.minecraft.client.gui.screen.WorldSelectionScreen(this));
        }));

        this.addButton(new Button(this.width / 2 - 150 / 2, this.height / 2 + 12, 150, 20, new StringTextComponent("Multiplayer"), (button) -> {
            Minecraft.getInstance().displayGuiScreen(new net.minecraft.client.gui.screen.MultiplayerScreen(this));
        }));

        this.addButton(new Button(this.width - 160, this.height - 30, 150, 20, new StringTextComponent("Exit"), (button) -> {
            Minecraft.getInstance().close();
        }));

        Button btn = new Button(10, this.height - 30, 50, 20, new StringTextComponent("N U T"), (button) -> {
            SimpleSound s = new SimpleSound(new SoundEvent(new ResourceLocation("cumclient", "nut")), SoundCategory.AMBIENT, 1.9F, 1.0F, new BlockPos(0, 0, 0));
            Minecraft.getInstance().getSoundHandler().play(s);
        });

        this.addButton(btn);

        matrixStack.pop();
        RenderSystem.disableBlend();
        //GL11.glScaled(10F,1,10F);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
