package org.occulteam.occultech.common.item;

import javax.swing.text.html.parser.Entity;

import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.common.capability.mana.CapRegistry;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WandItem extends Item {
    public int MANA_COST;
    public SoundEvent SOUND;
    public Entity PROJECTILE;
    public int COOLDOWN;

    public WandItem(Item.Properties properties, int manaCost, SoundEvent sound, Entity projectile, int cooldown) {
        super(properties);
        MANA_COST = manaCost;
        SOUND = sound;
        PROJECTILE = projectile;
        COOLDOWN = cooldown;
    }

    public int getManaCost() {
        return MANA_COST;
    }

    public void setManaCost(int manaCost) {
        MANA_COST = manaCost;
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean onCooldown = player.getCooldowns().isOnCooldown(this);

        if (onCooldown) {
            return InteractionResultHolder.pass(stack);
        }

        player.getCooldowns().addCooldown(this, COOLDOWN);
        CapRegistry.getMana(player).ifPresent(mana -> {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SOUND, SoundSource.PLAYERS, 1.0F, 1.0F);
        });
        player.awardStat(Stats.ITEM_USED.get(this));
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

}
