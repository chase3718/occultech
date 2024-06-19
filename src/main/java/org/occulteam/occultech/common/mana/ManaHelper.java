package org.occulteam.occultech.common.mana;

import net.minecraft.world.entity.LivingEntity;

public class ManaHelper {
    public static int getPlayerMana(LivingEntity entity) {
        return entity.getCapability(PlayerManaProvider.PLAYER_MANA).map(PlayerMana::getMana).orElse(0);
    }

    public static int getPlayerMaxMana(LivingEntity entity) {
        return entity.getCapability(PlayerManaProvider.PLAYER_MANA).map(PlayerMana::getMaxMana).orElse(0);
    }
}
