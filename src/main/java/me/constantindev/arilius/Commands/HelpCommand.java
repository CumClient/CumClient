package me.constantindev.arilius.Commands;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.HoverEvent;

import java.util.List;

public class HelpCommand extends CommandBase {
    public HelpCommand() {
        super("Help", "Displays all commands and modules", new String[]{"help", "h", "?", "man"});

    }

    @Override
    public void run(List<String> args) {
        Arilius.CMN.get().forEach(commandBase -> ClientHelper.SendClientNotif(new StringTextComponent("Command: " + commandBase.getName()).setStyle(Style.EMPTY.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("Description: " + commandBase.getDescription() + "\nTriggers: " + String.join(", ", commandBase.getTriggers())))))));
        Arilius.MMN.get().forEach(moduleBase -> ClientHelper.SendClientNotif(new StringTextComponent("Module: " + moduleBase.getName()).setStyle(Style.EMPTY.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("Description: " + moduleBase.getDescription() + "\nIs enabled? " + (moduleBase.isEnabled() ? "Yes" : "No")))))));
        super.run(args);
    }
}
