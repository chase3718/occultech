package org.occulteam.occultech.common.capability;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.api.mana.IManaCap;
import org.occulteam.occultech.common.network.Networking;
import org.occulteam.occultech.common.network.PacketSyncPlayerCap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CapabilityRegistry {
    public static final Capability<IManaCap> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<IPlayerCap> PLAYER_DATA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static LazyOptional<IManaCap> getMana(final LivingEntity entity) {
        if (entity == null) {
            return LazyOptional.empty();
        }
        return entity.getCapability(MANA_CAPABILITY);
    }

    public static LazyOptional<IPlayerCap> getPlayerDataCap(final LivingEntity entity) {
        if (entity == null)
            return LazyOptional.empty();
        return entity.getCapability(PLAYER_DATA_CAP);
    }

    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = Occultech.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                ManaCapAttacher.attach(event);
                PlayerCapAttacher.attach(event);
            }
        }

        @SubscribeEvent
        public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
            event.register(IManaCap.class);
            event.register(IPlayerCap.class);
        }

        @SubscribeEvent
        public static void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            getMana(oldPlayer).ifPresent(oldMaxMana -> getMana(event.getEntity()).ifPresent(newMaxMana -> {
                newMaxMana.setMaxMana(oldMaxMana.getMaxMana());
                newMaxMana.setMana(oldMaxMana.getCurrentMana());
            }));

            getPlayerDataCap(oldPlayer).ifPresent(oldPlayerCap -> {
                IPlayerCap playerDataCap = getPlayerDataCap(event.getEntity()).orElse(new PlayerDataCap());
                CompoundTag tag = oldPlayerCap.serializeNBT();
                playerDataCap.deserializeNBT(tag);
                syncPlayerCap(event.getEntity());
            });
            event.getOriginal().invalidateCaps();
        }

        @SubscribeEvent
        public static void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer) {
                syncPlayerCap(event.getEntity());
            }
        }

        @SubscribeEvent
        public static void respawnEvent(PlayerEvent.PlayerRespawnEvent event) {
            if (event.getEntity() instanceof ServerPlayer) {
                syncPlayerCap(event.getEntity());
            }
        }

        @SubscribeEvent
        public static void onPlayerStartTrackingEvent(PlayerEvent.StartTracking event) {
            if (event.getTarget() instanceof Player && event.getEntity() instanceof ServerPlayer) {
                syncPlayerCap(event.getEntity());
            }
        }

        @SubscribeEvent
        public static void onPlayerDimChangedEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (event.getEntity() instanceof ServerPlayer) {
                syncPlayerCap(event.getEntity());
            }
        }

        public static void syncPlayerCap(Player player) {
            IPlayerCap cap = CapabilityRegistry.getPlayerDataCap(player).orElse(new PlayerDataCap());
            CompoundTag tag = cap.serializeNBT();
            if (player instanceof ServerPlayer serverPlayer) {
                Networking.sendToPlayerClient(new PacketSyncPlayerCap(tag), serverPlayer);
            }
        }
    }
}
