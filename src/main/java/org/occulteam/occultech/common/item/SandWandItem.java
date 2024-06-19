// package org.occulteam.occultech.common.item;

// import org.jetbrains.annotations.NotNull;
// import org.occulteam.occultech.common.capability.PlayerCapHelper;

// import net.minecraft.stats.Stats;
// import net.minecraft.world.InteractionHand;
// import net.minecraft.world.InteractionResult;
// import net.minecraft.world.InteractionResultHolder;
// import net.minecraft.world.entity.player.Player;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.context.UseOnContext;
// import net.minecraft.world.level.Level;

// public class SandWandItem extends WandItem {
// public SandWandItem() {
// super(2);
// }

// @NotNull
// @Override
// public InteractionResult useOn(UseOnContext pContext) {
// Player player = pContext.getPlayer();

// if (player != null && PlayerCapHelper.hasEnoughMana(player, MANA_COST)) {
// player.startUsingItem(pContext.getHand());
// return InteractionResult.CONSUME;
// }

// return super.useOn(pContext);
// }

// @NotNull
// @Override
// public InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull
// Player pPlayer,
// @NotNull InteractionHand pUsedHand) {
// ItemStack stack = pPlayer.getItemInHand(pUsedHand);
// if (PlayerCapHelper.hasEnoughMana(pPlayer, MANA_COST)) {
// pPlayer.awardStat(Stats.ITEM_USED.get(this));
// pPlayer.startUsingItem(pUsedHand);
// return InteractionResultHolder.consume(stack);
// }

// return InteractionResultHolder.pass(stack);
// }
// }
