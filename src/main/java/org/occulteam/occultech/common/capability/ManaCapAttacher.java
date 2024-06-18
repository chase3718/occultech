package org.occulteam.occultech.common.capability;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.api.mana.IManaCap;

import cpw.mods.util.Lazy;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class ManaCapAttacher {
    private static class ManaCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        private static final ResourceLocation IDENTIFIER = new ResourceLocation(Occultech.MODID, "mana");

        private final IManaCap backend = new ManaCap(null);
        private final LazyOptional<IManaCap> optionalData = LazyOptional.of(() -> backend);

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return CapabilityRegistry.MANA_CAPABILITY.orEmpty(cap, this.optionalData);
        }

        void invalidate() {
            this.optionalData.invalidate();
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    public static void attach(final AttachCapabilitiesEvent<Entity> event) {

        event.addCapability(ManaCapProvider.IDENTIFIER, new ManaCapProvider());
    }
}
