package org.occulteam.occultech.common.item;

import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.startup.ModItems;

import java.util.function.Predicate;

public abstract class GunItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> BULLET_ONLY = (bul) -> bul.is(ModItems.BULLET.get());

    private int maxAmmo;
    private float shootingPower;

    public GunItem(Item.Properties pProperties, int pMaxAmmo, float pShootingPower) {
        super(pProperties.stacksTo(1));
        maxAmmo = pMaxAmmo;
        shootingPower = pShootingPower;
    }

    public void fire(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack gunInHand = pPlayer.getItemInHand(pHand);
        CompoundTag gunNBT = gunInHand.serializeNBT();
        gunNBT.putInt("curAmmo", gunNBT.getInt("curAmmo") - 1);
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
        pPlayer.sendSystemMessage(Component.literal(String.valueOf(gunNBT.getInt("curAmmo"))));
        SmallFireball smallFireball = new SmallFireball(pLevel, pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0);
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return BULLET_ONLY;
    }

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return BULLET_ONLY;
    }

    public void setCurAmmo(ItemStack gun, int ammo) {
        gun.serializeNBT().putInt("curAmmo", ammo);
    }

    public int getCurAmmo(ItemStack gun) {
        return gun.serializeNBT().getInt("curAmmo");
    }

    public void setMaxAmmo(int ammo) {
        maxAmmo = Math.max(1, ammo);
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void consumeAmmo(ItemStack gun) {
        CompoundTag gunNBT = gun.serializeNBT();
        gunNBT.putInt("curAmmo", gunNBT.getInt("curAmmo") - 1);
    }

    public float getShootingPower() {
        return shootingPower;
    }

    public boolean loadAmmo (Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.serializeNBT().get("curAmmo") == null) {
            CompoundTag nbtData = new CompoundTag();
            nbtData.putInt("curAmmo", 0);
            itemstack.setTag(nbtData);
        }
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
                setCurAmmo(itemstack, reloadct);
                pPlayer.sendSystemMessage(Component.literal(String.valueOf(itemstack.serializeNBT().get("curAmmo"))));
                pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F);
                return true;
            }

        } else {
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.0F);
            return false;
        }
    }
}