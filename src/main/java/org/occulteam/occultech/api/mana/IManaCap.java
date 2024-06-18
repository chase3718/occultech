package org.occulteam.occultech.api.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaCap extends INBTSerializable<CompoundTag> {
    double getCurrentMana();

    int getMaxMana();

    void setMaxMana(int maxMana);

    double setMana(final double mana);

    double addMana(final double mana);

    double consumeMana(final double mana);

}