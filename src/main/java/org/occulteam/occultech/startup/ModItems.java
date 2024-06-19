package org.occulteam.occultech.startup;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.common.item.FireballWandItem;
import org.occulteam.occultech.common.item.SandWandItem;
import org.occulteam.occultech.common.item.SnowballWandItem;
import org.occulteam.occultech.common.item.WandItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Occultech.MODID);

    public static final RegistryObject<WandItem> FIREBALL_WAND = ITEMS.register("fireball_wand", FireballWandItem::new);
    public static final RegistryObject<WandItem> SNOWBALL_WAND = ITEMS.register("snowball_wand", SnowballWandItem::new);
    public static final RegistryObject<WandItem> SAND_WAND = ITEMS.register("sand_wand", SandWandItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
