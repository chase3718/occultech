package org.occulteam.occultech.common.capability.mana;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class ManaCap implements IMana {

    private static final String NBT_KEY_MANA = "current_mana";
    private static final String NBT_KEY_MAX_MANA = "max_mana";
    private static final String NBT_KEY_MANA_REGEN = "mana_regen";

    private final LivingEntity entity;
    private int mana;
    private int maxMana;
    private int manaRegen;

    public ManaCap(@Nullable LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void setMana(int mana) {
        if (mana < 0) {
            this.mana = 0;
        } else if (mana > this.maxMana) {
            this.mana = this.maxMana;
        } else {
            this.mana = mana;
        }
    }

    @Override
    public void addMana(int mana) {
        if (this.mana + mana > this.maxMana) {
            this.mana = this.maxMana;
        } else if (this.mana + mana < 0) {
            this.mana = 0;

        } else {
            this.mana += mana;
        }
    }

    @Override
    public void removeMana(int mana) {
        if (this.mana - mana < 0) {
            this.mana = 0;
        } else if (this.mana - mana > this.maxMana) {
            this.mana = this.maxMana;
        } else {
            this.mana -= mana;
        }
    }

    @Override
    public void setMaxMana(int maxMana) {
        if (maxMana < 0) {
            this.maxMana = 0;
        } else {
            this.maxMana = maxMana;
        }
    }

    @Override
    public void addMaxMana(int maxMana) {
        if (this.maxMana + maxMana < 0) {
            this.maxMana = 0;
        } else {
            this.maxMana += maxMana;
        }
    }

    @Override
    public void regen() {
        if (this.mana < this.maxMana) {
            this.addMana(this.manaRegen);
        }
    }

    @Override
    public void setManaRegen(int manaRegen) {
        this.manaRegen = manaRegen;
    }

    @Override
    public int getManaRegen() {
        return this.manaRegen;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt(NBT_KEY_MANA, this.mana);
        nbt.putInt(NBT_KEY_MAX_MANA, this.maxMana);
        nbt.putInt(NBT_KEY_MANA_REGEN, this.manaRegen);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.mana = nbt.getInt(NBT_KEY_MANA);
        this.maxMana = nbt.getInt(NBT_KEY_MAX_MANA);
        this.manaRegen = nbt.getInt(NBT_KEY_MANA_REGEN);
    }

}