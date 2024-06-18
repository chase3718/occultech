package org.occulteam.occultech;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Occultech.MODID)
@Mod.EventBusSubscriber(modid = Occultech.MODID)
public class Occultech {
    public static final String MODID = "occultech";

    public Occultech() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // some common setup code
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

}
