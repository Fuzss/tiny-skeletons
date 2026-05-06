package fuzs.tinyskeletons.common.handler;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.event.v1.core.EventResultHolder;
import fuzs.tinyskeletons.common.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.function.Consumer;

public class SkeletonSpawningHandler {
    private static final Map<EntityType<?>, Holder<EntityType<?>>> BABY_SKELETON_VARIANTS = ImmutableMap.of(EntityType.SKELETON,
            (Holder<EntityType<?>>) ((Holder<?>) ModRegistry.BABY_SKELETON_ENTITY_TYPE),
            EntityType.WITHER_SKELETON,
            (Holder<EntityType<?>>) ((Holder<?>) ModRegistry.BABY_WITHER_SKELETON_ENTITY_TYPE),
            EntityType.STRAY,
            (Holder<EntityType<?>>) ((Holder<?>) ModRegistry.BABY_STRAY_ENTITY_TYPE),
            EntityType.BOGGED,
            (Holder<EntityType<?>>) ((Holder<?>) ModRegistry.BABY_BOGGED_ENTITY_TYPE),
            EntityType.PARCHED,
            (Holder<EntityType<?>>) ((Holder<?>) ModRegistry.BABY_PARCHED_ENTITY_TYPE));

    public static void onEntityLoad(Entity entity, ServerLevel serverLevel, boolean isLoadedFromDisk, @Nullable EntitySpawnReason entitySpawnReason) {
        // Exclude when summoned by command as this would break forcefully spawning an adult skeleton,
        // since there is no baby flag as with zombies which could force that otherwise.
        if (!isLoadedFromDisk && entitySpawnReason != null && entitySpawnReason != EntitySpawnReason.COMMAND
                && Zombie.getSpawnAsBabyOdds(serverLevel.getRandom())) {
            Holder<EntityType<?>> holder = BABY_SKELETON_VARIANTS.get(entity.getType());
            if (holder != null) {
                Entity babyEntity = spawnOffspring(serverLevel, holder.value(), entity, entitySpawnReason);
                if (babyEntity != null) {
                    entity.discard();
                }
            }
        }
    }

    public static EventResultHolder<InteractionResult> onUseEntity(Player player, Level level, InteractionHand interactionHand, Entity target, Vec3 hitVector) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (target.isAlive() && itemInHand.getItem() instanceof SpawnEggItem) {
            EntityType<?> entityType = SpawnEggItem.getType(itemInHand);
            Holder<EntityType<?>> holder = BABY_SKELETON_VARIANTS.get(entityType);
            if (holder != null && (target.getType() == holder.value() || target.getType() == entityType)) {
                if (level instanceof ServerLevel serverLevel) {
                    Entity entity = spawnOffspring(serverLevel,
                            holder.value(),
                            target,
                            EntitySpawnReason.SPAWN_ITEM_USE);
                    if (entity instanceof Mob mob) {
                        mob.applyComponentsFromItemStack(itemInHand);
                        itemInHand.consume(1, player);
                        player.awardStat(Stats.ITEM_USED.get(itemInHand.getItem()));
                    }
                }

                return EventResultHolder.interrupt(InteractionResult.SUCCESS);
            }
        }

        return EventResultHolder.pass();
    }

    /**
     * @see SpawnEggItem#spawnOffspringFromSpawnEgg(Player, Mob, EntityType, ServerLevel, Vec3, ItemStack)
     * @see EntityType#create(ServerLevel, Consumer, BlockPos, EntitySpawnReason, boolean, boolean)
     */
    @Nullable
    private static Entity spawnOffspring(ServerLevel serverLevel, EntityType<?> entityType, Entity adultEntity, EntitySpawnReason entitySpawnReason) {
        Entity babyEntity;
        if (adultEntity instanceof AgeableMob ageableMob) {
            babyEntity = ageableMob.getBreedOffspring(serverLevel, ageableMob);
        } else {
            babyEntity = entityType.create(serverLevel, entitySpawnReason);
        }

        if (babyEntity == null) {
            return null;
        } else {
            babyEntity.snapTo(adultEntity.getX(),
                    adultEntity.getY(),
                    adultEntity.getZ(),
                    Mth.wrapDegrees(serverLevel.getRandom().nextFloat() * 360.0F),
                    0.0F);
            if (babyEntity instanceof Mob mob) {
                mob.yHeadRot = babyEntity.getYRot();
                mob.yBodyRot = babyEntity.getYRot();
                DifficultyInstance difficulty = serverLevel.getCurrentDifficultyAt(adultEntity.blockPosition());
                mob.finalizeSpawn(serverLevel, difficulty, entitySpawnReason, null);
            }

            serverLevel.addFreshEntityWithPassengers(babyEntity);
            return babyEntity;
        }
    }
}
