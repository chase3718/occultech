package org.occulteam.occultech.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WandItem extends Item {
    public int MANA_COST = 10;

    public WandItem() {
        super(new Item.Properties().stacksTo(1));
    }

    public WandItem(int manaCost) {
        super(new Item.Properties().stacksTo(1));
        MANA_COST = manaCost;
    }

    public WandItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    public WandItem(Properties properties, int manaCost) {
        super(properties.stacksTo(1));
        MANA_COST = manaCost;
    }

    public int getManaCost() {
        return MANA_COST;
    }

    public void setManaCost(int manaCost) {
        MANA_COST = manaCost;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }
}
