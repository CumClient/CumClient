package me.constantindev.arilius.Etc.Mgr;

import me.constantindev.arilius.Commands.*;
import me.constantindev.arilius.Etc.Base.CommandBase;

import java.util.ArrayList;
import java.util.List;

public class CommandMGR {
    List<CommandBase> v = new ArrayList<>();

    public CommandMGR() {

        v.add(new TestCommand());
        v.add(new TriggerCommand());
        v.add(new ConfigCommand());
        v.add(new HelpCommand());
        v.add(new Bind());
        v.add(new Unbind());
    }

    public List<CommandBase> get() {
        return v;
    }
}
