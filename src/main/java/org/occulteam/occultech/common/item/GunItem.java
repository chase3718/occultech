package org.occulteam.occultech.common.item;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.jetbrains.annotations.NotNull;
import org.occulteam.occultech.startup.ModItems;

import java.util.function.Predicate;

public abstract class GunItem extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> BULLET_ONLY = (bul) -> bul.is(ModItems.BULLET.get());

    public GunItem(Item.Properties pProperties) {
        super(pProperties);
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return BULLET_ONLY;
    }

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return BULLET_ONLY;
    }
}