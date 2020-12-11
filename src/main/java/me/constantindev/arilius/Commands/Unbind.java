package me.constantindev.arilius.Commands;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import me.constantindev.arilius.Etc.KeyBindHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Unbind extends CommandBase {
    public Unbind() {
        super("Unbind", "Removes a keybind for a module", new String[]{"unbind", "ubind", "rmbind", "removebind"});
    }

    @Override
    public void run(List<String> args) {
        if (args.size() < 2) {
            ClientHelper.SendClientNotif("Please provide a keybind and a module to remove the keybindings of");
            return;
        }
        int kb = args.get(1).charAt(0);
        ModuleBase tr = Arilius.MMN.getModuleByName(args.get(0));
        if (tr == null) {
            ClientHelper.SendClientNotif("Module not found");
            return;
        }
        Map<KeyBindHelper, ModuleBase> newm = new HashMap<>();
        Map<KeyBindHelper, ModuleBase> rm = new HashMap<>();
        Arilius.bindings.forEach((keyBindHelper, moduleBase) -> {
            if (keyBindHelper.bind == kb && moduleBase.getName().equalsIgnoreCase(tr.getName())) {
                rm.put(keyBindHelper, moduleBase);
            } else newm.put(keyBindHelper, moduleBase);
        });
        Arilius.bindings = newm;
        ClientHelper.SendClientNotif("Removed binds:");
        rm.forEach((keyBindHelper, moduleBase) -> {
            ClientHelper.SendClientNotif(((char) keyBindHelper.bind) + ": " + moduleBase.getName());
        });
        super.run(args);
    }
}
