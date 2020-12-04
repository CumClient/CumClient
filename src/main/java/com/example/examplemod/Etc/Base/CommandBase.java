package com.example.examplemod.Etc.Base;

import java.util.ArrayList;
import java.util.List;

public class CommandBase {
    String name;
    String description;
    List<String> triggers = new ArrayList<>();

    public CommandBase(String name, String description, String[] triggers) {
        this.name = name;
        this.description = description;
        for (String s : triggers) this.triggers.add(s);
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTriggers() {
        return triggers;
    }

    public String getName() {
        return name;
    }

    public void run(List<String> args) {
    }
}
