package com.example.examplemod.Etc;

import com.example.examplemod.Etc.Base.ModuleBase;

import java.util.Map;

public class CheatConfigKey<T> {
    String key;
    T value;
    ModuleBase origin;
    public CheatConfigKey(String key, T value, ModuleBase origin) {
        this.key = key;
        this.value = value;
        this.origin = origin;
    }

    public String getKey() {
        return origin.getName()+"."+key;
    }
    public T getValue() {
        return value;
    }

    public ModuleBase getOrigin() {
        return origin;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
