package org.occultechteam.occultech.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.occultechteam.occultech.Occultech;

import net.neoforged.neoforge.registries.DeferredRegister;

public class OccultechItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Occultech.MODID);
    public static final DeferredItem<Item> WAND_OF_SPARKING = ITEMS.registerSimpleItem("WandOfSparking", new Item.Properties());
}
