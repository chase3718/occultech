package org.occulteam.occultech.common.capability;

import net.minecraft.world.entity.LivingEntity;

public class PlayerCapHelper {
    public static int getCurrentMana(LivingEntity entity) {
        if (entity != null) {
            return entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().getCurrentMana();
        }

        return -1;
    }

    public static int getMaxMana(LivingEntity entity) {
        if (entity != null) {
            return entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().getMaxMana();
        }

        return -1;
    }

    public static int setMana(LivingEntity entity, int mana) {
        if (entity != null) {
            return entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().setMana(mana);
        }

        return -1;
    }

    public static int setMaxMana(LivingEntity entity, int maxMana) {
        if (entity != null) {
            entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().setMaxMana(maxMana);
            return getMaxMana(entity);
        }
        return -1;
    }

    public static int addMana(LivingEntity entity, int mana) {
        if (entity != null) {
            return entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().addMana(mana);
        }

        return -1;
    }

    public static int consumeMana(LivingEntity entity, int mana) {
        if (entity != null && hasEnoughMana(entity, mana)) {
            return entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().consumeMana(mana);
        }

        return -1;
    }

    public static boolean hasEnoughMana(LivingEntity entity, int mana) {
        if (entity != null) {
            return entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().getCurrentMana() >= mana;
        }

        return false;
    }
}
