package com.example.examplemod.Etc;

import com.example.examplemod.Etc.Base.ModuleBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CheatConfigManager {
    List<CheatConfigKey> c = new ArrayList<>();

    public void addToConfig(CheatConfigKey v) {
        c.add(v);
    }

    public CheatConfigKey getByName(String name, ModuleBase origin) {
        AtomicReference<CheatConfigKey> ref = new AtomicReference<>(null);
        c.forEach(cheatConfigKey -> {
            if (cheatConfigKey.getKey().equalsIgnoreCase(origin.getName() + "." + name)) ref.set(cheatConfigKey);
        });
        return ref.get();
    }

    public List<CheatConfigKey> getByOrigin(ModuleBase origin) {
        List<CheatConfigKey> k = new ArrayList<>();
        c.forEach(cheatConfigKey -> {
            if (cheatConfigKey.getOrigin().getName().equalsIgnoreCase(origin.getName())) k.add(cheatConfigKey);
        });
        return k;
    }
}
