package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Etc.Base.ModuleBase;

public class PlayerInfo_EXT extends ModuleBase {
    public PlayerInfo_EXT() {
        super("PlayerInfo", "Shows information about all players over their heads, if they are in render distance");
    }
    // This module gets accessed externally, no tick code or enable code here. Just the on / off state.
}
