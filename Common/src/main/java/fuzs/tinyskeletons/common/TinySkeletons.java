package fuzs.tinyskeletons.common;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.EntityAttributesContext;
import fuzs.puzzleslib.common.api.core.v1.context.SpawnPlacementsContext;
import fuzs.puzzleslib.common.api.event.v1.entity.ServerEntityEvents;
import fuzs.puzzleslib.common.api.event.v1.entity.player.PlayerInteractEvents;
import fuzs.tinyskeletons.common.handler.SkeletonSpawningHandler;
import fuzs.tinyskeletons.common.init.ModRegistry;
import fuzs.tinyskeletons.common.util.BabySkeletonHelper;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabyStray;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TinySkeletons implements ModConstructor {
    public static final String MOD_ID = "tinyskeletons";
    public static final String MOD_NAME = "Tiny Skeletons";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        PlayerInteractEvents.USE_ENTITY.register(SkeletonSpawningHandler::onUseEntity);
        ServerEntityEvents.LOAD.register(SkeletonSpawningHandler::onEntityLoad);
    }

    @Override
    public void onRegisterSpawnPlacements(SpawnPlacementsContext context) {
        context.registerSpawnPlacement(ModRegistry.BABY_SKELETON_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        context.registerSpawnPlacement(ModRegistry.BABY_WITHER_SKELETON_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        context.registerSpawnPlacement(ModRegistry.BABY_STRAY_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BabyStray::checkBabyStraySpawnRules);
        context.registerSpawnPlacement(ModRegistry.BABY_BOGGED_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        context.registerSpawnPlacement(ModRegistry.BABY_PARCHED_ENTITY_TYPE.value(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkSurfaceMonstersSpawnRules);
    }

    @Override
    public void onRegisterEntityAttributes(EntityAttributesContext context) {
        context.registerAttributes(ModRegistry.BABY_SKELETON_ENTITY_TYPE.value(),
                BabySkeletonHelper.applyCommonAttributes(AbstractSkeleton.createAttributes()));
        context.registerAttributes(ModRegistry.BABY_WITHER_SKELETON_ENTITY_TYPE.value(),
                BabySkeletonHelper.applyCommonAttributes(AbstractSkeleton.createAttributes()));
        context.registerAttributes(ModRegistry.BABY_STRAY_ENTITY_TYPE.value(),
                BabySkeletonHelper.applyCommonAttributes(AbstractSkeleton.createAttributes()));
        context.registerAttributes(ModRegistry.BABY_BOGGED_ENTITY_TYPE.value(),
                BabySkeletonHelper.applyCommonAttributes(Bogged.createAttributes()));
        context.registerAttributes(ModRegistry.BABY_PARCHED_ENTITY_TYPE.value(),
                BabySkeletonHelper.applyCommonAttributes(Parched.createAttributes()));
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
