package org.occulteam.occultech.common.capability.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IMana extends INBTSerializable<CompoundTag> {
    int getMana();

    int getMaxMana();

    void setMana(int mana);

    void addMana(int mana);

    void removeMana(int mana);

    void setMaxMana(int maxMana);

    void addMaxMana(int maxMana);
}