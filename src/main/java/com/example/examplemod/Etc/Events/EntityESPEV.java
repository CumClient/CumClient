package com.example.examplemod.Etc.Events;

import com.example.examplemod.ExampleMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityESPEV {
    @SubscribeEvent
    public void RenderLivingEvent(net.minecraftforge.client.event.RenderLivingEvent ev) {
        if (ExampleMod.MMN.getModuleByName("entityesp").isEnabled()) ev.getEntity().setGlowing(true);
    }
}
