package org.occulteam.occultech.client.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.min;
import org.occulteam.occultech.common.capability.mana.CapRegistry;
import org.occulteam.occultech.common.capability.mana.IMana;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.overlay.ForgeGui;

@OnlyIn(Dist.CLIENT)
public class GuiManaBar {

        private static final Minecraft minecraft = Minecraft.getInstance();
        private static final ForgeGui gui = (ForgeGui) minecraft.gui;

        public static void DrawManaBar(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth,
                        int screenHeight) {
                int x = screenWidth / 2;
                int y = screenHeight;

                IMana mana = CapRegistry.getMana(minecraft.player).orElse(null);

                if (mana == null) {
                        guiGraphics.drawString(minecraft.font, Component.literal("BAD"), 100, 40, 0xFF0000FF);

                        return;
                }

                int curMana = mana.getMana();
                int maxMana = mana.getMaxMana();
                int percentage = (int) ((curMana / (float) maxMana) * 100);

                Component manaText = Component.literal("Mana: " + curMana + "/" + maxMana);
                Component time = Component.literal("Time: " + minecraft.level.getGameTime());

                guiGraphics.drawString(minecraft.font, manaText, 20, 20, 0xFFFFFFFF);
                guiGraphics.drawString(minecraft.font, time, 20, 40, 0xFFFFFFFF);
                // guiGraphics.fill(10, 10, 10 + (percentage * 2), 20, 0xFFFFFFFF);
        }

}
