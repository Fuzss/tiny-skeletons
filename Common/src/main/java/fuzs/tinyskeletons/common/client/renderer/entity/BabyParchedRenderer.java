package fuzs.tinyskeletons.common.client.renderer.entity;

import fuzs.tinyskeletons.common.TinySkeletons;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.model.monster.skeleton.BabySkeletonModel;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabyParched;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;

/**
 * @see net.minecraft.client.renderer.entity.ParchedRenderer
 */
public class BabyParchedRenderer extends AbstractSkeletonRenderer<BabyParched, SkeletonRenderState> {
    public static final Identifier BABY_PARCHED_TEXTURE = TinySkeletons.id("textures/entity/skeleton/parched_baby.png");
    public static final Identifier BABY_PARCHED_CLOTHES_TEXTURE = TinySkeletons.id(
            "textures/entity/skeleton/parched_baby_overlay.png");

    public BabyParchedRenderer(EntityRendererProvider.Context context) {
        this(context, ModModelLayers.BABY_PARCHED, ModModelLayers.BABY_PARCHED_ARMOR);
    }

    private BabyParchedRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer, ArmorModelSet<ModelLayerLocation> armorModelSet) {
        super(context, armorModelSet, new BabySkeletonModel<>(context.bakeLayer(modelLayer)));
        this.addLayer(new SkeletonClothingLayer<>(this,
                context.getModelSet(),
                ModModelLayers.BABY_PARCHED_OUTER_LAYER,
                BABY_PARCHED_CLOTHES_TEXTURE));
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    @Override
    public Identifier getTextureLocation(SkeletonRenderState state) {
        return BABY_PARCHED_TEXTURE;
    }
}
