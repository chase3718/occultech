package org.occulteam.occultech.setup;

import org.occulteam.occultech.Occultech;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.ItemStack;

public class CreativeTabsSetup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, Occultech.MODID);

    public static final RegistryObject<CreativeModeTab> OCCULTECH_TAB = CREATIVE_MODE_TABS.register("occultech",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STAFF.get()))
                    .title(Component.translatable("creativetab.occultech"))
                    .displayItems(CreativeTabsSetup::displayItems).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static void displayItems(CreativeModeTab.ItemDisplayParameters displayParameters,
            CreativeModeTab.Output output) {
        output.accept(ModItems.STAFF.get());
    }
}
