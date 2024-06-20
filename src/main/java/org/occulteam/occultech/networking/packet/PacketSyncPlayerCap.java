package org.occulteam.occultech.networking.packet;

import java.util.function.Supplier;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.common.capability.mana.CapRegistry;
import org.occulteam.occultech.common.capability.mana.IMana;
import org.occulteam.occultech.common.capability.mana.ManaCap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

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
            IMana cap = CapRegistry.getMana(playerEntity).orElse(new ManaCap(playerEntity));

            if (cap != null) {
                cap.deserializeNBT(tag);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
