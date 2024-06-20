package org.occulteam.occultech.common.capability.mana;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.Occultech;

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
    private static class ManaCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation ID = new ResourceLocation(Occultech.MODID, "mana");

        private final IMana backend = new ManaCap(null);
        private final LazyOptional<IMana> optionalData = LazyOptional.of(() -> this.backend);

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return CapRegistry.MANA_CAP.orEmpty(cap, this.optionalData);
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
        final ManaCapabilityProvider provider = new ManaCapabilityProvider();
        event.addCapability(ManaCapabilityProvider.ID, provider);
    }

    private ManaCapAttacher() {
    }
}
