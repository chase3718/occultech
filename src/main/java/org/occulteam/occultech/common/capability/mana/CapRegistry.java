package org.occulteam.occultech.common.capability.mana;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.networking.ModMessages;
import org.occulteam.occultech.networking.packet.ManaDataSyncS2CPacket;
import org.occulteam.occultech.networking.packet.PacketSyncPlayerCap;

import com.google.common.eventbus.Subscribe;

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

public class CapRegistry {

    public static final Capability<IMana> MANA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static LazyOptional<IMana> getMana(final LivingEntity entity) {
        if (entity == null) {
            return LazyOptional.empty();
        }
        return entity.getCapability(MANA_CAP);
    }

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IMana.class);
    }

    private CapRegistry() {
    }

    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = Occultech.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                ManaCapAttacher.attach(event);
            }
        }

        @SubscribeEvent
        public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
            event.register(IMana.class);
        }

        @SubscribeEvent
        public static void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            getMana(oldPlayer).ifPresent(oldMaxMana -> getMana(event.getEntity()).ifPresent(newMaxMana -> {
                newMaxMana.setMaxMana(oldMaxMana.getMaxMana());
                newMaxMana.setMana(oldMaxMana.getMana());

            }));
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
            IMana mana = CapRegistry.getMana(player).orElse(new ManaCap(player));
            CompoundTag nbt = mana.serializeNBT();
            if (player instanceof ServerPlayer serverPlayer) {
                ModMessages.sendToClient(new PacketSyncPlayerCap(nbt), serverPlayer);
            }
        }
    }
}