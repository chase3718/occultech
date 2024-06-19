package org.occulteam.occultech.startup;

import org.occulteam.occultech.Occultech;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Occultech.MODID)
public class Config {

    public static ForgeConfigSpec.IntValue MANABAR_X_OFFSET;
    public static ForgeConfigSpec.IntValue MANABAR_Y_OFFSET;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        MANABAR_X_OFFSET = CLIENT_BUILDER.comment("X offset for the Mana Bar").defineInRange("xManaBar", 0,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        MANABAR_Y_OFFSET = CLIENT_BUILDER.comment("Y offset for the Mana Bar").defineInRange("yManaBar", 0,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
