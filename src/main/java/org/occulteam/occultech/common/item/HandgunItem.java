package org.occulteam.occultech.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.occulteam.occultech.common.item.GunItem;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Predicate;

public class HandgunItem extends GunItem {
    public HandgunItem() {
        super(new Item.Properties(), 5, 1.6F);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (!pLevel.isClientSide) {
            if (getCurAmmo() != 0) {
                fire(pLevel, pPlayer);
            } else
                loadAmmo(pLevel, pPlayer, pHand);
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pHand));
    }

    public int getDefaultProjectileRange() {return 15;};
}