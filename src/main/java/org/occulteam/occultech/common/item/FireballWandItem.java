package org.occulteam.occultech.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireballWandItem extends Item {

    public FireballWandItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // Shoot a fireball out of the wand
        Vec3 look = pPlayer.getLookAngle();
        LargeFireball fireball = new LargeFireball(pLevel, pPlayer, 0, 0, 0, 1);
        fireball.setPos(pPlayer.getX() + look.x, pPlayer.getY() + look.y + pPlayer.getEyeHeight(),
                pPlayer.getZ() + look.z);
        fireball.setOwner(pPlayer);
        fireball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
        pLevel.addFreshEntity(fireball);

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
