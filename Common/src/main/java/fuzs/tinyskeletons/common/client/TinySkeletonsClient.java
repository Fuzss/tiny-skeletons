package fuzs.tinyskeletons.common.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.EntityRenderersContext;
import fuzs.puzzleslib.common.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.model.monster.skeleton.BabyBoggedModel;
import fuzs.tinyskeletons.common.client.model.monster.skeleton.BabySkeletonModel;
import fuzs.tinyskeletons.common.client.model.monster.skeleton.BabyWitherSkeletonModel;
import fuzs.tinyskeletons.common.client.renderer.entity.*;
import fuzs.tinyskeletons.common.init.ModRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class TinySkeletonsClient implements ClientModConstructor {

    @Override
    public void onRegisterEntityRenderers(EntityRenderersContext context) {
        context.registerEntityRenderer(ModRegistry.BABY_SKELETON_ENTITY_TYPE.value(), BabySkeletonRenderer::new);
        context.registerEntityRenderer(ModRegistry.BABY_WITHER_SKELETON_ENTITY_TYPE.value(),
                BabyWitherSkeletonRenderer::new);
        context.registerEntityRenderer(ModRegistry.BABY_STRAY_ENTITY_TYPE.value(), BabyStrayRenderer::new);
        context.registerEntityRenderer(ModRegistry.BABY_BOGGED_ENTITY_TYPE.value(), BabyBoggedRenderer::new);
        context.registerEntityRenderer(ModRegistry.BABY_PARCHED_ENTITY_TYPE.value(), BabyParchedRenderer::new);
        context.registerEntityRenderer(ModRegistry.THROWN_ITEM_ENTITY_TYPE.value(), ThrownItemRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModModelLayers.BABY_SKELETON, BabySkeletonModel::createBodyLayer);
        context.registerArmorDefinition(ModModelLayers.BABY_SKELETON_ARMOR,
                () -> HumanoidModel.createBabyArmorMeshSet(LayerDefinitions.BABY_INNER_ARMOR_DEFORMATION,
                        LayerDefinitions.BABY_OUTER_ARMOR_DEFORMATION,
                        PartPose.ZERO).map((MeshDefinition mesh) -> {
                    return LayerDefinition.create(mesh, 64, 64);
                }));
        context.registerLayerDefinition(ModModelLayers.BABY_STRAY, BabySkeletonModel::createBodyLayer);
        context.registerArmorDefinition(ModModelLayers.BABY_STRAY_ARMOR,
                () -> HumanoidModel.createBabyArmorMeshSet(LayerDefinitions.BABY_INNER_ARMOR_DEFORMATION,
                        LayerDefinitions.BABY_OUTER_ARMOR_DEFORMATION,
                        PartPose.ZERO).map((MeshDefinition mesh) -> {
                    return LayerDefinition.create(mesh, 64, 64);
                }));
        context.registerLayerDefinition(ModModelLayers.BABY_STRAY_OUTER_LAYER, () -> {
            return BabySkeletonModel.createBodyLayer(new CubeDeformation(0.2F));
        });
        context.registerLayerDefinition(ModModelLayers.BABY_WITHER_SKELETON, BabyWitherSkeletonModel::createBodyLayer);
        context.registerArmorDefinition(ModModelLayers.BABY_WITHER_SKELETON_ARMOR,
                () -> HumanoidModel.createBabyArmorMeshSet(LayerDefinitions.BABY_INNER_ARMOR_DEFORMATION,
                        LayerDefinitions.BABY_OUTER_ARMOR_DEFORMATION,
                        PartPose.ZERO).map((MeshDefinition mesh) -> {
                    return LayerDefinition.create(mesh, 64, 64);
                }));
        context.registerLayerDefinition(ModModelLayers.BABY_BOGGED, BabyBoggedModel::createBodyLayer);
        context.registerArmorDefinition(ModModelLayers.BABY_BOGGED_ARMOR,
                () -> HumanoidModel.createBabyArmorMeshSet(LayerDefinitions.BABY_INNER_ARMOR_DEFORMATION,
                        LayerDefinitions.BABY_OUTER_ARMOR_DEFORMATION,
                        PartPose.ZERO).map((MeshDefinition mesh) -> {
                    return LayerDefinition.create(mesh, 64, 64);
                }));
        context.registerLayerDefinition(ModModelLayers.BABY_BOGGED_OUTER_LAYER, () -> {
            return BabyBoggedModel.createBodyLayer(new CubeDeformation(0.2F));
        });
        context.registerLayerDefinition(ModModelLayers.BABY_PARCHED, BabySkeletonModel::createBodyLayer);
        context.registerLayerDefinition(ModModelLayers.BABY_PARCHED_OUTER_LAYER, () -> {
            return BabySkeletonModel.createBodyLayer(new CubeDeformation(0.2F));
        });
        context.registerArmorDefinition(ModModelLayers.BABY_PARCHED_ARMOR,
                () -> HumanoidModel.createBabyArmorMeshSet(LayerDefinitions.BABY_INNER_ARMOR_DEFORMATION,
                        LayerDefinitions.BABY_OUTER_ARMOR_DEFORMATION,
                        PartPose.ZERO).map((MeshDefinition mesh) -> {
                    return LayerDefinition.create(mesh, 64, 64);
                }));
    }
}
