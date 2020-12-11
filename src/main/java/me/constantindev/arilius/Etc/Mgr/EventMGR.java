package me.constantindev.arilius.Etc.Mgr;

import me.constantindev.arilius.Etc.Events.ChatEV;
import me.constantindev.arilius.Etc.Events.EntityESPEV;
import me.constantindev.arilius.Etc.Events.MainGUIEV;
import me.constantindev.arilius.Etc.Events.PlayerInfoEV;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventMGR {
    public EventMGR(IEventBus bus) {
        bus.register(new ChatEV());
        bus.register(new MainGUIEV());
        bus.register(new PlayerInfoEV());
        bus.register(new EntityESPEV());
    }

}
