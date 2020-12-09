package com.example.examplemod.Etc.Mgr;

import com.example.examplemod.Commands.*;
import com.example.examplemod.Etc.Base.CommandBase;

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
