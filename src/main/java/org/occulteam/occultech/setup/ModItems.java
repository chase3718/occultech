package org.occulteam.occultech.setup;

import org.occulteam.occultech.common.item.ModItem;
import org.occulteam.occultech.common.item.Staff;
import org.occulteam.occultech.common.lib.libItemNames;
import org.occulteam.occultech.common.util.RegistryWrapper;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static org.occulteam.occultech.Occultech.MODID;

@SuppressWarnings("Convert2MethodRef")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static RegistryWrapper<Staff> STAFF = register(libItemNames.STAFF, Staff::new);

    public static RegistryWrapper register(String name, Supplier<? extends Item> item) {
        return new RegistryWrapper<>(ITEMS.register(name, item));
    }

    public static RegistryWrapper register(String name) {
        return register(name, () -> new ModItem());
    }
}
