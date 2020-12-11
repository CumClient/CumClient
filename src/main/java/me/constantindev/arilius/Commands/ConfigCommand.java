package me.constantindev.arilius.Commands;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;

import java.util.List;

public class ConfigCommand extends CommandBase {
    public ConfigCommand() {
        super("Config", "Changes module configuration", new String[]{"conf", "config", "configuration"});
    }

    @Override
    public void run(List<String> args) {
        if (args.size() == 0) {
            ClientHelper.SendClientNotif("Here are all keys with their values");
            Arilius.MMN.get().forEach(moduleBase -> {
                Arilius.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                    ClientHelper.SendClientNotif(cheatConfigKey.getKey() + ": " + cheatConfigKey.getValue());
                });
            });
        } else if (args.size() == 1) {
            ClientHelper.SendClientNotif("Here are all keys of the " + args.get(0) + " module");
            Arilius.MMN.get().forEach(moduleBase -> {
                if (moduleBase.getName().equalsIgnoreCase(args.get(0))) {
                    Arilius.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                        ClientHelper.SendClientNotif(cheatConfigKey.getKey() + ": " + cheatConfigKey.getValue());
                    });
                }
            });
        } else if (args.size() == 2) {
            ClientHelper.SendClientNotif("Current value for the module and key: ");
            Arilius.MMN.get().forEach(moduleBase -> {
                if (moduleBase.getName().equalsIgnoreCase(args.get(0))) {
                    Arilius.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                        if (cheatConfigKey.getKey().split("[.]")[1].equalsIgnoreCase(args.get(1))) {
                            ClientHelper.SendClientNotif(cheatConfigKey.getKey() + ": " + cheatConfigKey.getValue());
                        }
                    });
                }
            });
        } else if (args.size() == 3) {
            ClientHelper.SendClientNotif("Setting value of " + args.get(1) + " for module " + args.get(0) + "...");
            Arilius.MMN.get().forEach(moduleBase -> {
                if (moduleBase.getName().equalsIgnoreCase(args.get(0))) {
                    Arilius.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                        if (cheatConfigKey.getKey().split("[.]")[1].equalsIgnoreCase(args.get(1))) {
                            ClientHelper.SendClientNotif(cheatConfigKey.getKey() + ": " + cheatConfigKey.getValue() + " -> " + cheatConfigKey.getKey() + ": " + args.get(2));
                            cheatConfigKey.setValue(args.get(2));
                        }
                    });
                }
            });
        }
        super.run(args);
    }
}
