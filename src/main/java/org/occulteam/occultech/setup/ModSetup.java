package org.occulteam.occultech.setup;

import static org.occulteam.occultech.Occultech.MODID;

import net.minecraftforge.eventbus.api.IEventBus;

public class ModSetup {

    public static void register(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
    }
}
