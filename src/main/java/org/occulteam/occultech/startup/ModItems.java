package org.occulteam.occultech.startup;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.common.item.FireballWandItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Occultech.MODID);

    public static final RegistryObject<Item> FIREBALL_WAND = ITEMS.register("fireball_wand", FireballWandItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
