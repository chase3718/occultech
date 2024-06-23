package org.occulteam.occultech.common.item;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.common.capability.mana.CapRegistry;
import org.occulteam.occultech.common.capability.mana.IMana;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WandItem extends Item {
    public int MANA_COST;
    public SoundEvent SOUND;
    public Supplier<? extends Projectile> PROJECTILE;
    public int COOLDOWN;

    public WandItem(Item.Properties properties, int manaCost, SoundEvent sound,
            Supplier<? extends Projectile> projectile,
            int cooldown) {
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
        if (level.isClientSide) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        ItemStack stack = player.getItemInHand(hand);
        boolean onCooldown = player.getCooldowns().isOnCooldown(this);

        if (onCooldown) {
            return InteractionResultHolder.pass(stack);
        }

        IMana mana = CapRegistry.getMana(player).orElse(null);

        if (mana == null) {
            return InteractionResultHolder.fail(stack);
        }

        if (mana.getMana() < MANA_COST) {
            Component manaMsg = Component.literal("Not enough mana!");
            player.displayClientMessage(manaMsg, false);
            return InteractionResultHolder.fail(stack);
        }
        player.getCooldowns().addCooldown(this, COOLDOWN);

        mana.removeMana(MANA_COST);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SOUND, SoundSource.PLAYERS, 1.0F, 1.0F);
        player.awardStat(Stats.ITEM_USED.get(this));
        player.startUsingItem(hand);
        fireProjectile(level, player);
        return InteractionResultHolder.consume(stack);
    }

    private void fireProjectile(Level level, Player player) {
        try {
        } catch (Exception e) {
            CapRegistry.getMana(player).ifPresent(mana -> mana.addMana(MANA_COST));
            player.displayClientMessage(Component.literal(e.toString()), false);
            e.printStackTrace();
        }
    }
}
