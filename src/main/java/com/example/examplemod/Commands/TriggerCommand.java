package com.example.examplemod.Commands;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.ExampleMod;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TriggerCommand extends CommandBase {
    public TriggerCommand() {
        super("Trigger", "Triggers a module", new String[]{"t", "trigger", "toggle"});
    }

    @Override
    public void run(List<String> args) {
        if (args.size() < 1) {
            ClientHelper.SendClientNotif("Please fucking give me a module to trigger");
            return;
        }
        AtomicReference<ModuleBase> M = new AtomicReference<>(null);
        ExampleMod.MMN.get().forEach(moduleBase -> {
            if (moduleBase.getName().toLowerCase().equals(args.get(0).toLowerCase())) M.set(moduleBase);
        });
        if (M.get() == null) {
            ClientHelper.SendClientNotif("bruh that module just kinda doesnt exist");
            return;
        }
        M.get().setEnabled(!M.get().isEnabled());

        super.run(args);
    }
}
