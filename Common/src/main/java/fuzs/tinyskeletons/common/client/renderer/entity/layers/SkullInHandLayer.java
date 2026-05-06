package fuzs.tinyskeletons.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.puzzleslib.common.api.client.renderer.v1.layers.SimpleItemInHandLayer;
import fuzs.tinyskeletons.common.client.renderer.entity.state.BabyWitherSkeletonRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;

/**
 * @see net.minecraft.client.renderer.entity.layers.CarriedBlockLayer
 */
public class SkullInHandLayer<S extends BabyWitherSkeletonRenderState, M extends EntityModel<S> & ArmedModel<S>> extends SimpleItemInHandLayer<S, M> {

    public SkullInHandLayer(RenderLayerParent<S, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        if (!state.skullModel.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.0F, -0.075F, 0.325F);
            poseStack.translate(0.0F, 0.6875F, -0.75F);
            poseStack.mulPose(Axis.XP.rotationDegrees(20.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
            poseStack.translate(0.25F, 0.1875F, 0.25F);
            poseStack.scale(-0.5F, -0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            state.skullModel.submit(poseStack,
                    submitNodeCollector,
                    lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    state.outlineColor);
            poseStack.popPose();
        } else {
            super.submit(poseStack, submitNodeCollector, lightCoords, state, yRot, xRot);
        }
    }
}
