package me.constantindev.arilius.Etc.Events;

import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import me.constantindev.arilius.Etc.Mgr.CommandMGR;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ChatEV {

    @SubscribeEvent
    public void ClientChatEvent(ClientChatEvent ev) {
        if (ev.getMessage().startsWith(".")) {

            ev.setCanceled(true);
            Minecraft.getInstance().ingameGUI.getChatGUI().addToSentMessages(ev.getMessage());
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
