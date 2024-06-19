package org.occulteam.occultech.common.item;

import org.occulteam.occultech.common.capability.PlayerCapHelper;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SnowballWandItem extends WandItem {

    public SnowballWandItem() {
        super(5);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // Check if the player has enough mana
        if (!PlayerCapHelper.hasEnoughMana(pPlayer, MANA_COST)) {
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }

        // Consume 5 mana
        PlayerCapHelper.consumeMana(pPlayer, MANA_COST);

        // Report mana amount
        String text = "Mana: ";

        if (pPlayer != null) {
            String curMana = PlayerCapHelper.getCurrentMana(pPlayer) + "/" + PlayerCapHelper.getMaxMana(pPlayer);
            text = text.concat(curMana);
        }

        PlayerChatMessage message = PlayerChatMessage.unsigned(pPlayer.getUUID(), text);
        pPlayer.createCommandSourceStack().sendChatMessage(new OutgoingChatMessage.Player(message), false,
                ChatType.bind(ChatType.CHAT, pPlayer));
        // Play a sound
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS, 1.0F, 1.0F);

        // Play a particle effect
        pLevel.addParticle(ParticleTypes.ITEM_SNOWBALL, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), 0.0D, 0.0D,
                0.0D);

        // Shoot a snowball out of the wand
        Vec3 look = pPlayer.getLookAngle();
        Snowball snowball = new Snowball(pLevel, pPlayer);
        snowball.setPos(pPlayer.getX() + look.x, pPlayer.getY() + look.y + pPlayer.getEyeHeight(),
                pPlayer.getZ() + look.z);
        snowball.setOwner(pPlayer);
        snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
        pLevel.addFreshEntity(snowball);

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
