package org.occulteam.occultech.startup;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.common.item.WandItem;
import org.occulteam.occultech.common.item.BulletItem;
import org.occulteam.occultech.common.item.RevolverItem;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Occultech.MODID);

    public static final RegistryObject<Item> FIREBALL_WAND = ITEMS.register("fireball_wand",
            () -> new WandItem(new Item.Properties(), 150, SoundEvents.BLAZE_SHOOT, SmallFireball::new, 10));

    public static final RegistryObject<Item> BULLET = ITEMS.register("bullet", BulletItem::new);
    public static final RegistryObject<Item> REVOLVER = ITEMS.register("revolver", RevolverItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
