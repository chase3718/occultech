package org.occulteam.occultech.api.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaCap extends INBTSerializable<CompoundTag> {
    int getCurrentMana();

    int getMaxMana();

    void setMaxMana(int maxMana);

    int setMana(final int mana);

    int addMana(final int mana);

    int consumeMana(final int mana);

}