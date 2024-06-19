package org.occulteam.occultech.client;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.startup.ModItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            Occultech.MODID);

    public static final RegistryObject<CreativeModeTab> OCCULTECH_TAB = CREATIVE_TABS.register("occultech_tab",
            () -> CreativeModeTab
                    .builder()
                    .icon(() -> new ItemStack(ModItems.FIREBALL_WAND.get()))
                    .title(Component.translatable("creativetab.occultech"))
                    .displayItems(CreativeModTab::addDisplayItems)
                    .build());

    private static void addDisplayItems(CreativeModeTab.ItemDisplayParameters displayParameters,
            CreativeModeTab.Output output) {
        output.accept(new ItemStack(ModItems.FIREBALL_WAND.get()));
        output.accept(new ItemStack(ModItems.BULLET.get()));
        output.accept(new ItemStack(ModItems.HANDGUN.get()));
        // output.accept(new ItemStack(ModItems.SNOWBALL_WAND.get()));
        // output.accept(new ItemStack(ModItems.SAND_WAND.get()));

    }

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }

}
