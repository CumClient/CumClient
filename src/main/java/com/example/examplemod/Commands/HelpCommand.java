package com.example.examplemod.Commands;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.ExampleMod;
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
        ExampleMod.CMN.get().forEach(commandBase -> ClientHelper.SendClientNotif(new StringTextComponent("Command: " + commandBase.getName()).setStyle(Style.EMPTY.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("Description: " + commandBase.getDescription() + "\nTriggers: " + String.join(", ", commandBase.getTriggers())))))));
        ExampleMod.MMN.get().forEach(moduleBase -> ClientHelper.SendClientNotif(new StringTextComponent("Module: " + moduleBase.getName()).setStyle(Style.EMPTY.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("Description: " + moduleBase.getDescription() + "\nIs enabled? " + (moduleBase.isEnabled() ? "Yes" : "No")))))));
        super.run(args);
    }
}
