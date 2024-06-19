package org.occulteam.occultech.networking.packet;

import java.util.function.Supplier;

import org.occulteam.occultech.client.ClientManaData;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ManaDataSyncS2CPacket {
    private final int mana;

    public ManaDataSyncS2CPacket(int mana) {
        this.mana = mana;
    }

    public ManaDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.mana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ClientManaData.setMana(this.mana);
        });
        return true;
    }
}
