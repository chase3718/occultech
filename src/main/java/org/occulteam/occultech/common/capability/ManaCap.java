package org.occulteam.occultech.common.capability;

import javax.annotation.Nullable;

import org.occulteam.occultech.api.mana.IManaCap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class ManaCap implements IManaCap {
    private final LivingEntity livingEntity;
    private int mana;
    private int maxMana;

    public ManaCap(@Nullable final LivingEntity _livingEntity) {
        livingEntity = _livingEntity;
    }

    @Override
    public int getCurrentMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void setMaxMana(final int _maxMana) {
        maxMana = _maxMana;
    }

    @Override
    public int setMana(final int _mana) {
        if (_mana > getMaxMana()) {
            mana = getMaxMana();
        } else if (_mana < 0) {
            mana = 0;
        } else {
            mana = _mana;
        }

        return getCurrentMana();
    }

    @Override
    public int addMana(final int addMana) {
        return setMana(getCurrentMana() + addMana);
    }

    @Override
    public int consumeMana(final int _mana) {
        if (_mana < 0) {
            return getCurrentMana();
        }

        return setMana(getCurrentMana() - _mana);
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt("current_mana", getCurrentMana());
        tag.putInt("max_mana", getMaxMana());
        return tag;
    }

    @Override
    public void deserializeNBT(final CompoundTag tag) {
        setMana(tag.getInt("current_mana"));
        setMaxMana(tag.getInt("max_mana"));
    }
}
