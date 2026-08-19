package fuzs.tinyskeletons.common.client.renderer.entity;

import fuzs.tinyskeletons.common.TinySkeletons;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.model.monster.skeleton.BabySkeletonModel;
import fuzs.tinyskeletons.common.client.renderer.entity.state.BabySkeletonRenderState;
import fuzs.tinyskeletons.common.init.ModRegistry;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabySkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/**
 * @see net.minecraft.client.renderer.entity.SkeletonRenderer
 */
public class BabySkeletonRenderer extends AbstractSkeletonRenderer<BabySkeleton, BabySkeletonRenderState> {
    public static final Identifier BABY_SKELETON_TEXTURE = TinySkeletons.id("textures/entity/skeleton/skeleton_baby.png");

    public BabySkeletonRenderer(EntityRendererProvider.Context context) {
        this(context, ModModelLayers.BABY_SKELETON, ModModelLayers.BABY_SKELETON_ARMOR);
    }

    private BabySkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer, ArmorModelSet<ModelLayerLocation> armorModelSet) {
        super(context, armorModelSet, new BabySkeletonModel<>(context.bakeLayer(modelLayer)));
    }

    @Override
    public Identifier getTextureLocation(BabySkeletonRenderState state) {
        return BABY_SKELETON_TEXTURE;
    }

    @Override
    public void extractRenderState(BabySkeleton entity, BabySkeletonRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        if (state.getOffHandItemStack().is(ModRegistry.BABY_SKELETON_PRIMARY_WEAPONS_ITEM_TAG)
                || state.getOffHandItemStack().is(ModRegistry.BABY_SKELETON_SECONDARY_WEAPONS_ITEM_TAG)) {
            state.getOffHandItemState().clear();
        }
    }

    @Override
    public BabySkeletonRenderState createRenderState() {
        return new BabySkeletonRenderState();
    }
}
