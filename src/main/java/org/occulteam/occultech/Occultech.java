package org.occulteam.occultech;

import org.occulteam.occultech.client.CreativeModTab;
import org.occulteam.occultech.common.command.AddManaCommand;
import org.occulteam.occultech.common.command.SetMaxManaCommand;
import org.occulteam.occultech.common.command.ShowManaCommand;
import org.occulteam.occultech.networking.ModMessages;
import org.occulteam.occultech.startup.ClientProxy;
import org.occulteam.occultech.startup.IProxy;
import org.occulteam.occultech.startup.ModItems;
import org.occulteam.occultech.startup.ServerProxy;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Occultech.MODID)
@Mod.EventBusSubscriber(modid = Occultech.MODID)
public class Occultech {
    public static final String MODID = "occultech";
    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public Occultech() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CreativeModTab.register(modEventBus);

        ModItems.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }

    private void clientSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @SubscribeEvent
    public static void RegisterCommands(RegisterCommandsEvent event) {
        ShowManaCommand.register(event.getDispatcher());
        AddManaCommand.register(event.getDispatcher());
        SetMaxManaCommand.register(event.getDispatcher());
    }

}
