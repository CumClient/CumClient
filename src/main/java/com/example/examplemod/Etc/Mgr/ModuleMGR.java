package com.example.examplemod.Etc.Mgr;

import com.example.examplemod.Etc.Base.ModuleBase;
import com.example.examplemod.Modules.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleMGR {
    List<ModuleBase> v = new ArrayList<>();

    public ModuleMGR() {

        v.add(new Killaura());
        v.add(new Nuker());
    }

    public List<ModuleBase> get() {
        return v;
    }
}
