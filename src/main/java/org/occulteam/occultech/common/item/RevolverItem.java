package org.occulteam.occultech.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RevolverItem extends GunItem {
    public RevolverItem() {
        super(new Item.Properties(), 6, 1.6F);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack gun = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide) {
            if (getCurAmmo(gun) != 0) {
                fire(pLevel, pPlayer, pHand);
            } else
                loadAmmo(pLevel, pPlayer, pHand);
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pHand));
    }

    public int getDefaultProjectileRange() {return 15;};
}