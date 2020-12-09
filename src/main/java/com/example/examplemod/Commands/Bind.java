package com.example.examplemod.Commands;

import com.example.examplemod.Etc.Base.CommandBase;
import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Etc.Helper.ClientHelper;
import com.example.examplemod.Etc.KeyBindHelper;
import com.example.examplemod.ExampleMod;

import java.util.List;


public class Bind extends CommandBase {
    public Bind() {
        super("Bind", "Binds a command to a key", new String[]{"bind", "b"});
    }


    @Override
    public void run(List<String> args) {
        if (args.size() < 2) {
            ClientHelper.SendClientNotif("Please provide a module to trigger and a key to trigger it with");
            return;
        }
        int kc = args.get(1).toUpperCase().charAt(0);
        ModuleBase m = ExampleMod.MMN.getModuleByName(args.get(0));
        if (m == null) {
            ClientHelper.SendClientNotif("That module does not exist. Please try a valid one");
            return;
        }
        ExampleMod.bindings.put(new KeyBindHelper(kc), m);
        ClientHelper.SendClientNotif(ExampleMod.bindings.toString());
        super.run(args);
    }
}
