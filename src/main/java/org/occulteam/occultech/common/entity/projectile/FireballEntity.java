package org.occulteam.occultech.common.entity.projectile;

import org.occulteam.occultech.setup.ModEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public class FireballEntity extends Projectile {
    public FireballEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireballEntity(Level pLevel) {
        super(EntityType.FIREBALL, pLevel);
    }

    public FireballEntity(Level pLevel, LivingEntity livingEntity) {
        super(EntityType.FIREBALL, pLevel);
    }

    // Implement the defineSynchedData() method
    @Override
    protected void defineSynchedData() {
        // Add your implementation here
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(), Blocks.BIRCH_LOG.defaultBlockState(), 3);
        }
        this.discard();
    }
}
