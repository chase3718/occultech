package org.occulteam.occultech.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.occulteam.occultech.common.item.GunItem;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Predicate;

public class HandgunItem extends GunItem {
    private static final float shootingPower = 1.6F;
    public HandgunItem() {
        super(new Item.Properties());
        //maxAmmo = 5;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
//        ItemStack itemstack = pPlayer.getItemInHand(pHand);
//        if (!pPlayer.getProjectile(itemstack).isEmpty()) {
//            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 0.5F);
//            return InteractionResultHolder.consume(itemstack);
//        } else
//            return InteractionResultHolder.fail(itemstack);
        loadAmmo(pLevel, pPlayer, pHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pHand));
    }

    public float getShootingPower() {return shootingPower;};

    public int getDefaultProjectileRange() {return 15;};
}