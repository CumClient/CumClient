package com.example.examplemod.Etc.Mgr;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Modules.AutoWalk;
import com.example.examplemod.Modules.Killaura;
import com.example.examplemod.Modules.Nuker;
import com.example.examplemod.Modules.Scaffold;

import java.util.ArrayList;
import java.util.List;

public class ModuleMGR {
    List<ModuleBase> v = new ArrayList<>();

    public ModuleMGR() {

        v.add(new Killaura());
        v.add(new Nuker());
        v.add(new AutoWalk());
        v.add(new Scaffold());
    }

    public List<ModuleBase> get() {
        return v;
    }
}
