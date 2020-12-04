package com.example.examplemod.Etc.Mgr;

import com.example.examplemod.Etc.Events.ChatEV;
import com.example.examplemod.Etc.Events.MainGUIEV;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventMGR {
    public EventMGR(IEventBus bus) {
        bus.register(new ChatEV());
        bus.register(new MainGUIEV());
    }

}
