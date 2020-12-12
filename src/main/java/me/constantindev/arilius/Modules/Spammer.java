package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.CheatConfigKey;
import me.constantindev.arilius.Etc.CheatConfigManager;
import net.minecraft.client.Minecraft;

public class Spammer extends ModuleBase {
    public Spammer() {
        super("Spammer","Spams a ton of messages");
    }

    @Override
    public void register(CheatConfigManager config) {
        config.addToConfig(new CheatConfigKey<>("amount",-1,this));
        config.addToConfig(new CheatConfigKey<>("message","GET FUCKED",this));
        super.register(config);
    }

    @Override
    public void run() {
        int amount = Integer.parseInt(Arilius.config.getByName("amount",this).getValue().toString());
        String msg = Arilius.config.getByName("message",this).getValue().toString();
        if (amount < 0) {
            Minecraft.getInstance().player.sendChatMessage(msg+" - "+(Math.random()*1000));
        } else {
            for(int i = 0;i<amount;i++) {
                Minecraft.getInstance().player.sendChatMessage(msg+" - "+(Math.random()*1000));
            }
            this.setEnabled(false);
        }
        super.run();
    }
}
