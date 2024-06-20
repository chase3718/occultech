package org.occulteam.occultech.client.gui;

import org.occulteam.occultech.common.capability.mana.CapRegistry;
import org.occulteam.occultech.common.capability.mana.IMana;
import org.occulteam.occultech.startup.Config;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class GuiManaBar {
        // public static final IGuiOverlay OVERLAY = GuiManaBar::renderOverlay;

        private static final Minecraft minecraft = Minecraft.getInstance();

        public static final IGuiOverlay OVERLAY = ((gui, poseStack, partialTick, width, height) -> {
                int x = width / 2;
                int y = height;

                IMana mana = CapRegistry.getMana(minecraft.player).orElse(null);

                if (mana == null) {
                        return;
                }

                int curMana = mana.getMana();
                int maxMana = mana.getMaxMana();
                int percentage = (int) ((float) curMana / (float) maxMana * 100);

                int barWidth = percentage * 2;
                int barHeight = 10;

                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        });
}
