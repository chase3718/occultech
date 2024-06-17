package org.occulteam.occultech.common.item;

import org.occulteam.occultech.common.entity.projectile.FireballEntity;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Staff extends ModItem {

    public Staff(Properties pProperties) {
        super(pProperties);
    }

    public Staff() {
        super(new Item.Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F,
                0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!pLevel.isClientSide()) {
            FireballEntity fireball = new FireballEntity(pLevel, pPlayer);
        }

        return InteractionResultHolder.success(itemstack);
    }

}
