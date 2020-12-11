package me.constantindev.arilius.Commands;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TriggerCommand extends CommandBase {
    public TriggerCommand() {
        super("Trigger", "Triggers a module", new String[]{"t", "trigger", "toggle"});
    }

    @Override
    public void run(List<String> args) {
        if (args.size() < 1) {
            ClientHelper.SendClientNotif("Please provide a module name to trigger");
            return;
        }
        AtomicReference<ModuleBase> M = new AtomicReference<>(null);
        Arilius.MMN.get().forEach(moduleBase -> {
            if (moduleBase.getName().toLowerCase().equals(args.get(0).toLowerCase())) M.set(moduleBase);
        });
        if (M.get() == null) {
            ClientHelper.SendClientNotif("Module not found");
            return;
        }
        if (M.get().isEnabled()) {
            ClientHelper.SendClientNotif("Disabled module");
            M.get().onDisable();
        } else {
            ClientHelper.SendClientNotif("Enabled module");
            M.get().onEnable();
        }
        M.get().setEnabled(!M.get().isEnabled());

        super.run(args);
    }
}
