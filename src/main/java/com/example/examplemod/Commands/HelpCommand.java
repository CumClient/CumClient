package com.example.examplemod.Commands;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.ExampleMod;

import java.util.List;

public class HelpCommand extends CommandBase {
    public HelpCommand() {
        super("Help", "Displays all commands and modules", new String[]{"help", "h", "?", "man"});

    }

    @Override
    public void run(List<String> args) {
        ExampleMod.CMN.get().forEach(commandBase -> ClientHelper.SendClientNotif("Command: " + commandBase.getName() + "\n  Description: " + commandBase.getDescription() + "\n  Triggers: " + String.join(", ", commandBase.getTriggers())));
        ExampleMod.MMN.get().forEach(moduleBase -> ClientHelper.SendClientNotif("Module: " + moduleBase.getName() + "\n  Is enabled: " + (moduleBase.isEnabled() ? "Yes" : "No") + "\n  Description: " + moduleBase.getDescription()));
        super.run(args);
    }
}
