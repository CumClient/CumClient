package com.example.examplemod.Etc.Events;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.Etc.Mgr.CommandMGR;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ChatEV {
    @SubscribeEvent
    public void ServerChatEvent(ServerChatEvent ev) {
        if (ev.getMessage().startsWith(".") && ev.getPlayer().getUniqueID().equals(Minecraft.getInstance().player.getUniqueID())) {
            ev.setCanceled(true);
            String[] args = ev.getMessage().substring(1).split(" +");
            List<String> argsc = new ArrayList<>();
            String cmd = args[0].toLowerCase();
            args[0] = null;
            for (String s : args) {
                if (s != null) argsc.add(s);
            }
            List<CommandBase> commands = new CommandMGR().get();
            AtomicReference<CommandBase> b = new AtomicReference<>(null);
            commands.forEach(commandBase -> {
                if (commandBase.getTriggers().contains(cmd)) b.set(commandBase);
            });
            if (b.get() == null) {
                ClientHelper.SendClientNotif("Command " + cmd + " not found.");
                return;
            }
            b.get().run(argsc);
        }
    }
}
