package org.occulteam.occultech.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.startup.ModItems;

import java.util.function.Predicate;

public abstract class GunItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> BULLET_ONLY = (bul) -> bul.is(ModItems.BULLET.get());

    private int maxAmmo;
    private int curAmmo;
    private float shootingPower;

    public GunItem(Item.Properties pProperties, int pMaxAmmo, float pShootingPower) {
        super(pProperties);
        maxAmmo = pMaxAmmo;
        shootingPower = pShootingPower;
        curAmmo = 0;
    }

    public void fire(Level pLevel, Player pPlayer) {
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
        curAmmo--;
        pPlayer.sendSystemMessage(Component.literal(String.valueOf(curAmmo)));
        SmallFireball smallFireball = new SmallFireball(pLevel, pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0);
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return BULLET_ONLY;
    }

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return BULLET_ONLY;
    }

    public void setCurAmmo(int ammo) {
        curAmmo = Math.min(ammo, maxAmmo);
    }

    public int getCurAmmo() {
        return curAmmo;
    }

    public void setMaxAmmo(int ammo) {
        maxAmmo = Math.max(1, ammo);
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void consumeAmmo() {
        curAmmo = Math.max(0, curAmmo-1);
    }

    public float getShootingPower() {
        return shootingPower;
    }

    public boolean loadAmmo (Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!pPlayer.getProjectile(itemstack).isEmpty()) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem)itemstack.getItem()).getAllSupportedProjectiles();
            ItemStack ammoItemStack = null;
            for(int i = 0; i < pPlayer.getInventory().getContainerSize(); ++i) {
                ItemStack itemstack1 = pPlayer.getInventory().getItem(i);
                if (predicate.test(itemstack1)) {
                    ammoItemStack = pPlayer.getInventory().getItem(i);
                    break;
                }
            }
            if (ammoItemStack == null) {
                pPlayer.displayClientMessage(Component.literal("Oops! There was an issue reloading!"), true);
                return false;
            } else {
                int reloadct = (int) Math.min(maxAmmo, ammoItemStack.getCount());
                ammoItemStack.split(reloadct);
                curAmmo = reloadct;
                pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F);
                return true;
            }

        } else {
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.0F);
            return false;
        }
    }
}