package fuzs.tinyskeletons.common.client.renderer.entity;

import fuzs.puzzleslib.common.api.client.renderer.v1.layers.SimpleHumanoidArmorLayer;
import fuzs.puzzleslib.common.api.client.renderer.v1.layers.SimpleItemInHandLayer;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.packs.VanillaTexture;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabyStray;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;

/**
 * @see net.minecraft.client.renderer.entity.StrayRenderer
 */
public class BabyStrayRenderer extends AbstractSkeletonRenderer<BabyStray, SkeletonRenderState> {
    public static final VanillaTexture STRAY_SKELETON_TEXTURE = new VanillaTexture("textures/entity/skeleton/stray.png");
    public static final VanillaTexture STRAY_CLOTHES_TEXTURE = new VanillaTexture(
            "textures/entity/skeleton/stray_overlay.png");

    public BabyStrayRenderer(EntityRendererProvider.Context context) {
        this(context, ModModelLayers.BABY_STRAY, ModModelLayers.BABY_STRAY_ARMOR);
    }

    private BabyStrayRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer, ArmorModelSet<ModelLayerLocation> armorModelSet) {
        super(context, modelLayer, armorModelSet);
        this.layers.removeIf((RenderLayer<SkeletonRenderState, SkeletonModel<SkeletonRenderState>> renderLayer) -> {
            return renderLayer instanceof ItemInHandLayer || renderLayer instanceof HumanoidArmorLayer;
        });
        this.addLayer(new SimpleItemInHandLayer<>(this));
        this.addLayer(new SimpleHumanoidArmorLayer<>(this,
                ArmorModelSet.bake(armorModelSet, context.getModelSet(), SkeletonModel::new),
                context.getEquipmentRenderer()));
        this.addLayer(new SkeletonClothingLayer<>(this,
                context.getModelSet(),
                ModModelLayers.BABY_STRAY_OUTER_LAYER,
                STRAY_CLOTHES_TEXTURE.id()));
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    @Override
    public Identifier getTextureLocation(SkeletonRenderState state) {
        return STRAY_SKELETON_TEXTURE.id();
    }
}
