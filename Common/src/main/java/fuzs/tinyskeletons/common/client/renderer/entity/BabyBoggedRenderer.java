package fuzs.tinyskeletons.common.client.renderer.entity;

import fuzs.puzzleslib.common.api.client.renderer.v1.layers.SimpleHumanoidArmorLayer;
import fuzs.puzzleslib.common.api.client.renderer.v1.layers.SimpleItemInHandLayer;
import fuzs.tinyskeletons.common.client.model.geom.ModModelLayers;
import fuzs.tinyskeletons.common.client.packs.VanillaTexture;
import fuzs.tinyskeletons.common.world.entity.monster.skeleton.BabyBogged;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.skeleton.BoggedModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.BoggedRenderState;
import net.minecraft.resources.Identifier;

/**
 * @see net.minecraft.client.renderer.entity.BoggedRenderer
 */
public class BabyBoggedRenderer extends AbstractSkeletonRenderer<BabyBogged, BoggedRenderState> {
    public static final VanillaTexture BOGGED_SKELETON_TEXTURE = new VanillaTexture(
            "textures/entity/skeleton/bogged.png");
    public static final VanillaTexture BOGGED_OUTER_LAYER_TEXTURE = new VanillaTexture(
            "textures/entity/skeleton/bogged_overlay.png");

    public BabyBoggedRenderer(EntityRendererProvider.Context context) {
        this(context, ModModelLayers.BABY_BOGGED, ModModelLayers.BABY_BOGGED_ARMOR);
    }

    private BabyBoggedRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayer, ArmorModelSet<ModelLayerLocation> armorModelSet) {
        super(context, armorModelSet, new BoggedModel(context.bakeLayer(modelLayer)));
        this.layers.removeIf((RenderLayer<BoggedRenderState, SkeletonModel<BoggedRenderState>> renderLayer) -> {
            return renderLayer instanceof ItemInHandLayer || renderLayer instanceof HumanoidArmorLayer;
        });
        this.addLayer(new SimpleItemInHandLayer<>(this));
        this.addLayer(new SimpleHumanoidArmorLayer<>(this,
                ArmorModelSet.bake(armorModelSet, context.getModelSet(), SkeletonModel::new),
                context.getEquipmentRenderer()));
        this.addLayer(new SkeletonClothingLayer<>(this,
                context.getModelSet(),
                ModModelLayers.BABY_BOGGED_OUTER_LAYER,
                BOGGED_OUTER_LAYER_TEXTURE.id()));
    }

    @Override
    public BoggedRenderState createRenderState() {
        return new BoggedRenderState();
    }

    @Override
    public void extractRenderState(BabyBogged babyBogged, BoggedRenderState state, float partialTick) {
        super.extractRenderState(babyBogged, state, partialTick);
        state.isSheared = babyBogged.isSheared();
    }

    @Override
    public Identifier getTextureLocation(BoggedRenderState state) {
        return BOGGED_SKELETON_TEXTURE.id();
    }
}
