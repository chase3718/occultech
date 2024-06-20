package org.occulteam.occultech.common.event;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.networking.ModMessages;
import org.occulteam.occultech.networking.packet.ManaDataSyncS2CPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class ModEvents {

    // @SubscribeEvent
    // public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity>
    // event) {
    // if (event.getObject() instanceof Player) {
    // if
    // (!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent())
    // {
    // event.addCapability(new ResourceLocation(Occultech.MODID, "properties"), new
    // PlayerManaProvider());
    // }
    // }
    // }

    // @SubscribeEvent
    // public static void onPlayerCloned(PlayerEvent.Clone event) {
    // if (event.isWasDeath()) {
    // event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore
    // -> {
    // event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore
    // -> {
    // newStore.copyFrom(oldStore);
    // });
    // });
    // }
    // }

    // @SubscribeEvent
    // public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
    // event.register(PlayerMana.class);
    // }

    // @SubscribeEvent
    // public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    // if (event.side == LogicalSide.SERVER) {
    // event.player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana ->
    // {
    // if (mana.getMana() < mana.getMaxMana()) {
    // mana.regen();
    // }
    // });
    // }
    // }

    // @SubscribeEvent
    // public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
    // if (!event.getLevel().isClientSide()) {
    // if (event.getEntity() instanceof ServerPlayer player) {
    // player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
    // ModMessages.sendToClient(new ManaDataSyncS2CPacket(mana.getMana()), player);
    // });
    // }
    // }
    // }
}
