package me.constantindev.arilius;

import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.CheatConfigManager;
import me.constantindev.arilius.Etc.ControllerServer.HTTPControllerServerMGR;
import me.constantindev.arilius.Etc.KeyBindHelper;
import me.constantindev.arilius.Etc.Mgr.CommandMGR;
import me.constantindev.arilius.Etc.Mgr.EventMGR;
import me.constantindev.arilius.Etc.Mgr.ModuleMGR;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("arilius")
public class Arilius {
    public static int tick;
    public static ModuleMGR MMN = new ModuleMGR();
    public static CommandMGR CMN = new CommandMGR();
    public static CheatConfigManager config = new CheatConfigManager();
    public static Map<KeyBindHelper, ModuleBase> bindings = new HashMap<>();
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Arilius() throws IOException {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        new EventMGR(MinecraftForge.EVENT_BUS);
        MMN.get().forEach(moduleBase -> {
            moduleBase.register(config);
            LOGGER.info("Successfully loaded module " + moduleBase.getName() + " into memory and made it ready to be enabled!");
        });
        Thread srvThread = new Thread(() -> {
            try {
                new HTTPControllerServerMGR();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }, "Controller thread");
        srvThread.start();
        Minecraft.getInstance().getMainWindow().setWindowIcon(
                Minecraft.getInstance().getPackFinder().getVanillaPack().getResourceStream(ResourcePackType.CLIENT_RESOURCES,new ResourceLocation("arilius","icon.png"))
                , Minecraft.getInstance().getPackFinder().getVanillaPack().getResourceStream(ResourcePackType.CLIENT_RESOURCES,new ResourceLocation("arilius","icon.png")));
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
