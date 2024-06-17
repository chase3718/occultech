package org.occulteam.occultech.setup;

import net.minecraftforge.eventbus.api.IEventBus;

public class ModSetup {

    public static void register(IEventBus modEventBus) {
        ModItems.register(modEventBus);
    }
}
