package org.occulteam.occultech.common.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private int MAX_MANA;
    private int MIN_MANA;
    private int MANA_REGEN;
    private int REGEN_BOOST;

    public int getMana() {
        return mana;
    }

    public int regen() {
        return this.addMana(MANA_REGEN + REGEN_BOOST);
    }

    public int getManaRegen() {
        return MANA_REGEN;
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public int addMana(int mana) {
        this.mana = Math.min(MAX_MANA, this.mana + mana);
        return this.mana;
    }

    public void consumeMana(int mana) {
        this.mana = Math.max(MIN_MANA, this.mana - mana);
    }

    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", this.mana);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.mana = nbt.getInt("mana");
    }
}
