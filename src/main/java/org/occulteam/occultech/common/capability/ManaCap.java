package org.occulteam.occultech.common.capability;

import javax.annotation.Nullable;

import org.occulteam.occultech.api.mana.IManaCap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

public class ManaCap implements IManaCap {
    private final LivingEntity livingEntity;
    private double mana;
    private int maxMana;

    public ManaCap(@Nullable final LivingEntity _livingEntity) {
        livingEntity = _livingEntity;
    }

    @Override
    public double getCurrentMana() {
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
    public double setMana(final double _mana) {
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
    public double addMana(final double addMana) {
        return setMana(getCurrentMana() + addMana);
    }

    @Override
    public double consumeMana(final double _mana) {
        if (_mana < 0) {
            return getCurrentMana();
        }

        return setMana(getCurrentMana() - _mana);
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putDouble("current_mana", getCurrentMana());
        tag.putInt("max_mana", getMaxMana());
        return tag;
    }

    @Override
    public void deserializeNBT(final CompoundTag tag) {
        setMana(tag.getDouble("current_mana"));
        setMaxMana(tag.getInt("max_mana"));
    }
}
