package me.constantindev.arilius.Etc.Mgr;

import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Modules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ModuleMGR {
    List<ModuleBase> v = new ArrayList<>();

    public ModuleMGR() {

        v.add(new Killaura());
        v.add(new Nuker());
        v.add(new AutoWalk());
        v.add(new Scaffold());
        v.add(new NoFall());
        v.add(new Tunnel());
        v.add(new PlayerInfo_EXT());
        v.add(new EntityESP_EXT());
        v.add(new Fly());
        v.add(new AutoTool());
    }

    public ModuleBase getModuleByName(String n) {
        AtomicReference<ModuleBase> f = new AtomicReference<>(null);
        this.get().forEach(moduleBase -> {
            if (moduleBase.getName().equalsIgnoreCase(n)) f.set(moduleBase);
        });
        return f.get();
    }

    public List<ModuleBase> get() {
        return v;
    }
}
