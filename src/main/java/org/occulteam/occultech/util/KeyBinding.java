package org.occulteam.occultech.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_GUNS = "key.category.occultech.guns";
    public static final String KEY_RELOAD_GUN = "key.occultech.reload_gun";

    public static final KeyMapping RELOAD_KEY = new KeyMapping(KEY_RELOAD_GUN, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_GUNS);
}
