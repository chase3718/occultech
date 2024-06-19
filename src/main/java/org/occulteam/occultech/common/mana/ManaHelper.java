package org.occulteam.occultech.common.mana;

import net.minecraft.world.entity.LivingEntity;

public class ManaHelper {
    // public static int getPlayerMana(LivingEntity entity) {
    // int pMana = -1;
    // entity.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana ->
    // mana.getMana());

    // }

    public static int getPlayerMaxMana(LivingEntity entity) {
        return entity.getCapability(PlayerManaProvider.PLAYER_MANA).resolve().get().getMaxMana();
    }

    public static int setPlayerMaxMana(LivingEntity entity, int maxMana) {
        return entity.getCapability(PlayerManaProvider.PLAYER_MANA).resolve().get().setMaxMana(maxMana);
    }
}
