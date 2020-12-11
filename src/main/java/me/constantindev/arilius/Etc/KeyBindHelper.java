package me.constantindev.arilius.Etc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;

public class KeyBindHelper {
    public int bind;
    boolean pressedb4 = false;

    public KeyBindHelper(int orig) {
        bind = orig;
    }

    public boolean isPressed() {
        boolean isPressed = InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), bind);
        if (!isPressed) pressedb4 = false;
        if (pressedb4) return false;
        if (isPressed) {
            pressedb4 = true;
            return true;
        } else return false;

    }
}
