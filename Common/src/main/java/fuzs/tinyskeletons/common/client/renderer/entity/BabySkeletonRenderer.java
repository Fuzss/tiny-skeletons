package fuzs.tinyskeletons.common.client.renderer.entity;

import fuzs.tinyskeletons.common.TinySkeletons;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.model.monster.skeleton.BabySkeletonModel;
import fuzs.tinyskeletons.common.client.renderer.entity.layers.ItemInMainHandLayer;
import fuzs.tinyskeletons.common.client.renderer.entity.layers.ItemOnBackLayer;
import fuzs.tinyskeletons.common.client.renderer.entity.state.BabySkeletonRenderState;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabySkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
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
        this.layers.removeIf(ItemInHandLayer.class::isInstance);
        this.addLayer(new ItemInMainHandLayer<>(this));
        this.addLayer(new ItemOnBackLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(BabySkeletonRenderState state) {
        return BABY_SKELETON_TEXTURE;
    }

    @Override
    public void extractRenderState(BabySkeleton babySkeleton, BabySkeletonRenderState state, float partialTick) {
        super.extractRenderState(babySkeleton, state, partialTick);
        state.offhandItemType = BabySkeletonRenderState.getItemType(babySkeleton.getOffhandItem().getItem());
        ItemStackRenderState itemStackRenderState = BabySkeletonRenderState.getOffHandItem(state);
        this.itemModelResolver.updateForLiving(itemStackRenderState,
                babySkeleton.getItemHeldByArm(state.mainArm.getOpposite()),
                state.offhandItemType.getItemDisplayContext(),
                babySkeleton);
    }

    @Override
    public BabySkeletonRenderState createRenderState() {
        return new BabySkeletonRenderState();
    }
}
