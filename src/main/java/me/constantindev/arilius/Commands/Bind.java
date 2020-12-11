package me.constantindev.arilius.Commands;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.CommandBase;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import me.constantindev.arilius.Etc.KeyBindHelper;

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
        ModuleBase m = Arilius.MMN.getModuleByName(args.get(0));
        if (m == null) {
            ClientHelper.SendClientNotif("That module does not exist. Please try a valid one");
            return;
        }
        Arilius.bindings.put(new KeyBindHelper(kc), m);
        ClientHelper.SendClientNotif(Arilius.bindings.toString());
        super.run(args);
    }
}
