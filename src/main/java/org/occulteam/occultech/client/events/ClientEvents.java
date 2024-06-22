package org.occulteam.occultech.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.client.gui.GuiManaBar;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.occulteam.occultech.common.item.GunItem;
import org.occulteam.occultech.util.KeyBinding;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    @Mod.EventBusSubscriber(modid = Occultech.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void registerOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("mana_bar", GuiManaBar::DrawManaBar);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.RELOAD_KEY);
        }
    }

    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed R"));

            if (KeyBinding.RELOAD_KEY.consumeClick()) {
                Player pPlayer = Minecraft.getInstance().player;
                Level pLevel = Minecraft.getInstance().player.level();

                if (pPlayer.getInventory().getSelected().getItem() instanceof GunItem) {
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed R"));
                    ((GunItem) pPlayer.getInventory().getSelected().getItem()).loadAmmo(pLevel, pPlayer, InteractionHand.MAIN_HAND);
                }
            }
        }
    }
}