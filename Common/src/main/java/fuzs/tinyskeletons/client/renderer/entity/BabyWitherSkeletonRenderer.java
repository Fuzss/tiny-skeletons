package fuzs.tinyskeletons.client.renderer.entity;

import fuzs.tinyskeletons.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.client.model.monster.skeleton.BabyWitherSkeletonModel;
import fuzs.tinyskeletons.client.packs.VanillaTexture;
import fuzs.tinyskeletons.client.renderer.entity.layers.SkullInHandLayer;
import fuzs.tinyskeletons.client.renderer.entity.state.BabyWitherSkeletonRenderState;
import fuzs.tinyskeletons.world.entity.monster.skeleton.BabyWitherSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @see net.minecraft.client.renderer.entity.WitherSkeletonRenderer
 */
public class BabyWitherSkeletonRenderer extends AbstractSkeletonRenderer<BabyWitherSkeleton, BabyWitherSkeletonRenderState> {
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    public static final VanillaTexture WITHER_SKELETON_TEXTURE = new VanillaTexture(
            "textures/entity/skeleton/wither_skeleton.png");

    private final BlockModelResolver blockModelResolver;

    public BabyWitherSkeletonRenderer(EntityRendererProvider.Context context) {
        this(context, ModModelLayers.BABY_WITHER_SKELETON, ModModelLayers.BABY_WITHER_SKELETON_ARMOR);
    }

    protected BabyWitherSkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayerLocation, ArmorModelSet<ModelLayerLocation> armorModelSet) {
        super(context, armorModelSet, new BabyWitherSkeletonModel(context.bakeLayer(modelLayerLocation)));
        this.blockModelResolver = context.getBlockModelResolver();
        this.layers.removeIf((RenderLayer<BabyWitherSkeletonRenderState, SkeletonModel<BabyWitherSkeletonRenderState>> renderLayer) -> {
            return renderLayer instanceof HumanoidArmorLayer || renderLayer instanceof ItemInHandLayer;
        });
        this.addLayer(new SkullInHandLayer<>(this));
        this.addLayer(new HumanoidArmorLayer<>(this,
                ArmorModelSet.bake(armorModelSet, context.getModelSet(), BabyWitherSkeletonModel::new),
                context.getEquipmentRenderer()));
    }

    @Override
    public void extractRenderState(BabyWitherSkeleton abstractSkeleton, BabyWitherSkeletonRenderState state, float partialTick) {
        super.extractRenderState(abstractSkeleton, state, partialTick);
        state.isDancing = abstractSkeleton.isDancing();
        BlockState blockState = abstractSkeleton.getSkullBlock();
        if (blockState != null) {
            this.blockModelResolver.update(state.skullModel, blockState, BLOCK_DISPLAY_CONTEXT);
        } else {
            state.skullModel.clear();
        }
    }

    @Override
    public BabyWitherSkeletonRenderState createRenderState() {
        return new BabyWitherSkeletonRenderState();
    }

    @Override
    public Identifier getTextureLocation(BabyWitherSkeletonRenderState state) {
        return WITHER_SKELETON_TEXTURE.id();
    }
}
