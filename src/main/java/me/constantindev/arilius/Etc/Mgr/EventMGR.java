package me.constantindev.arilius.Etc.Mgr;

import me.constantindev.arilius.Etc.Events.*;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventMGR {
    public EventMGR(IEventBus bus) {
        bus.register(new ChatEV());
        bus.register(new MainGUIEV());
        bus.register(new PlayerInfoEV());
        bus.register(new EntityESPEV());
    }

}
