package org.occulteam.occultech.common.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class AbstractProjectile extends ThrowableItemProjectile {

    public AbstractProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType,
            Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        // Implement the defineSynchedData() method here
    }

    @Override
    public Item getDefaultItem() {
        // Implement the getDefaultItem() method here
        return Items.FIRE_CHARGE;
    }
}
