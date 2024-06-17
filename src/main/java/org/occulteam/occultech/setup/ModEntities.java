package org.occulteam.occultech.setup;

import org.occulteam.occultech.Occultech;
import org.occulteam.occultech.common.entity.projectile.FireballEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister
            .create(ForgeRegistries.ENTITY_TYPES, Occultech.MODID);

    public static final RegistryObject<EntityType<FireballEntity>> FIREBALL = ENTITY_TYPES.register("fireball",
            () -> EntityType.Builder.<FireballEntity>of(FireballEntity::new, MobCategory.MISC).sized(0.3125F, 0.3125F)
                    .clientTrackingRange(8).updateInterval(20).build("fireball"));
}
