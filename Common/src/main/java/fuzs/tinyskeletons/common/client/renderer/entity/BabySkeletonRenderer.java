package fuzs.tinyskeletons.common.client.renderer.entity;

import fuzs.puzzleslib.common.api.client.renderer.v1.layers.SimpleHumanoidArmorLayer;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.packs.VanillaTexture;
import fuzs.tinyskeletons.common.client.renderer.entity.layers.ItemInMainHandLayer;
import fuzs.tinyskeletons.common.client.renderer.entity.layers.ItemOnBackLayer;
import fuzs.tinyskeletons.common.client.renderer.entity.state.BabySkeletonRenderState;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabySkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.Identifier;

/**
 * @see net.minecraft.client.renderer.entity.SkeletonRenderer
 */
public class BabySkeletonRenderer extends AbstractSkeletonRenderer<BabySkeleton, BabySkeletonRenderState> {
    public static final VanillaTexture SKELETON_TEXTURE = new VanillaTexture("textures/entity/skeleton/skeleton.png");

    public BabySkeletonRenderer(EntityRendererProvider.Context context) {
        this(context, ModModelLayers.BABY_SKELETON, ModModelLayers.BABY_SKELETON_ARMOR);
    }

    private BabySkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer, ArmorModelSet<ModelLayerLocation> armorModelSet) {
        super(context, modelLayer, armorModelSet);
        this.layers.removeIf((RenderLayer<BabySkeletonRenderState, SkeletonModel<BabySkeletonRenderState>> renderLayer) -> {
            return renderLayer instanceof ItemInHandLayer || renderLayer instanceof HumanoidArmorLayer;
        });
        this.addLayer(new SimpleHumanoidArmorLayer<>(this,
                ArmorModelSet.bake(armorModelSet, context.getModelSet(), SkeletonModel::new),
                context.getEquipmentRenderer()));
        this.addLayer(new ItemInMainHandLayer<>(this));
        this.addLayer(new ItemOnBackLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(BabySkeletonRenderState state) {
        return SKELETON_TEXTURE.id();
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
