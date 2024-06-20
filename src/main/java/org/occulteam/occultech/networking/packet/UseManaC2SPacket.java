package org.occulteam.occultech.networking.packet;

import com.google.common.base.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.ForgeConfig.Server;
import net.minecraftforge.network.NetworkEvent;

public class UseManaC2SPacket {

    public UseManaC2SPacket() {
    }

    public UseManaC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player != null) {

            }
        });
        return true;
    }
}
