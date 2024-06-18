package org.occulteam.occultech.common.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.common.capability.CapabilityRegistry;
import org.occulteam.occultech.common.capability.IPlayerCap;
import org.occulteam.occultech.common.capability.PlayerDataCap;

public class PacketSyncPlayerCap {
    CompoundTag tag;

    // Decoder
    public PacketSyncPlayerCap(FriendlyByteBuf buf) {
        tag = buf.readNbt();
    }

    // Encoder
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(tag);
    }

    public PacketSyncPlayerCap(CompoundTag famCaps) {
        this.tag = famCaps;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player playerEntity = Occultech.proxy.getPlayer();
            IPlayerCap cap = CapabilityRegistry.getPlayerDataCap(playerEntity).orElse(new PlayerDataCap());

            if (cap != null) {
                cap.deserializeNBT(tag);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}