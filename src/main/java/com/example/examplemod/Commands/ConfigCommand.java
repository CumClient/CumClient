package com.example.examplemod.Commands;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.ExampleMod;

import java.util.List;

public class ConfigCommand extends CommandBase {
    public ConfigCommand() {
        super("Config","Changes module configuration",new String[]{"conf","config","configuration"});
    }

    @Override
    public void run(List<String> args) {
        if(args.size() == 0) {
            ClientHelper.SendClientNotif("Here are all keys with their values");
            ExampleMod.MMN.get().forEach(moduleBase -> {
                ExampleMod.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                    ClientHelper.SendClientNotif(cheatConfigKey.getKey()+": "+cheatConfigKey.getValue());
                });
            });
        } else if (args.size() == 1) {
            ClientHelper.SendClientNotif("Here are all keys of the "+args.get(0)+" module");
            ExampleMod.MMN.get().forEach(moduleBase -> {
                if (moduleBase.getName().equalsIgnoreCase(args.get(0))) {
                    ExampleMod.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                        ClientHelper.SendClientNotif(cheatConfigKey.getKey()+": "+cheatConfigKey.getValue());
                    });
                }
            });
        } else if (args.size() == 2) {
            ClientHelper.SendClientNotif("Current value for the module and key: ");
            ExampleMod.MMN.get().forEach(moduleBase -> {
                if (moduleBase.getName().equalsIgnoreCase(args.get(0))) {
                    ExampleMod.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                        if (cheatConfigKey.getKey().split("[.]")[1].equalsIgnoreCase(args.get(1))) {
                            ClientHelper.SendClientNotif(cheatConfigKey.getKey()+": "+cheatConfigKey.getValue());
                        }
                    });
                }
            });
        } else if (args.size() == 3) {
            ClientHelper.SendClientNotif("Setting value of "+args.get(1)+" for module "+args.get(0)+"...");
            ExampleMod.MMN.get().forEach(moduleBase -> {
                if (moduleBase.getName().equalsIgnoreCase(args.get(0))) {
                    ExampleMod.config.getByOrigin(moduleBase).forEach(cheatConfigKey -> {
                        if (cheatConfigKey.getKey().split("[.]")[1].equalsIgnoreCase(args.get(1))) {
                            ClientHelper.SendClientNotif(cheatConfigKey.getKey()+": "+cheatConfigKey.getValue()+" -> "+cheatConfigKey.getKey()+": "+args.get(2));
                            cheatConfigKey.setValue(args.get(2));
                        }
                    });
                }
            });
        }
        super.run(args);
    }
}
