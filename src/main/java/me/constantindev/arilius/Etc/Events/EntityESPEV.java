package me.constantindev.arilius.Etc.Events;

import me.constantindev.arilius.Arilius;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityESPEV {
    @SubscribeEvent
    public void RenderLivingEvent(net.minecraftforge.client.event.RenderLivingEvent ev) {
        if (Arilius.MMN.getModuleByName("entityesp").isEnabled()) ev.getEntity().setGlowing(true);
    }
}
