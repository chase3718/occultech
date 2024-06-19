package org.occulteam.occultech.common.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private int MAX_MANA = 10;
    private int MIN_MANA = 0;
    private int MANA_REGEN = 1;
    private int REGEN_BOOST = 0;

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

    public int consumeMana(int mana) {
        this.mana = Math.max(MIN_MANA, this.mana - mana);
        return this.mana;
    }

    public int setMaxMana(int maxMana) {
        this.MAX_MANA = maxMana;
        return maxMana;
    }

    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", this.mana);
        nbt.putInt("MAX_MANA", this.MAX_MANA);
        nbt.putInt("MIN_MANA", this.MIN_MANA);
        nbt.putInt("MANA_REGEN", this.MANA_REGEN);
        nbt.putInt("REGEN_BOOST", this.REGEN_BOOST);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.mana = nbt.getInt("mana");
        this.MAX_MANA = nbt.getInt("MAX_MANA");
        this.MIN_MANA = nbt.getInt("MIN_MANA");
        this.MANA_REGEN = nbt.getInt("MANA_REGEN");
        this.REGEN_BOOST = nbt.getInt("REGEN_BOOST");
    }
}
