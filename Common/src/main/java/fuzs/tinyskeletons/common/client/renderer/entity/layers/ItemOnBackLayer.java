package fuzs.tinyskeletons.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.tinyskeletons.common.client.renderer.entity.state.BabySkeletonRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class ItemOnBackLayer<M extends HumanoidModel<BabySkeletonRenderState> & ArmedModel<BabySkeletonRenderState>> extends RenderLayer<BabySkeletonRenderState, M> {

    public ItemOnBackLayer(RenderLayerParent<BabySkeletonRenderState, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, BabySkeletonRenderState state, float yRot, float xRot) {
        ItemStackRenderState itemState = BabySkeletonRenderState.getOffHandItem(state);
        if (!itemState.isEmpty()) {
            poseStack.pushPose();
            ModelPart modelPart = this.getParentModel().body;
            modelPart.translateAndRotate(poseStack);
            float backOffset = !state.chestEquipment.isEmpty() ? 0.24F : 0.18F;
            if (state.offhandItemType != BabySkeletonRenderState.ItemType.TRIDENT) {
                poseStack.translate(0.0, 0.0, backOffset);
                poseStack.scale(2.0F, 2.0F, 2.0F);
                if (state.offhandItemType == BabySkeletonRenderState.ItemType.ROD) {
                    poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
                    poseStack.translate(0.0, -0.3, 0.0);
                }
            } else {
                poseStack.mulPose(Axis.YP.rotationDegrees(52.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(40.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-25.0F));
                poseStack.scale(1.0F, -1.0F, -1.0F);
                poseStack.translate(-backOffset, 0.0, 0.0);
            }

            itemState.submit(poseStack,
                    submitNodeCollector,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    state.outlineColor);
            poseStack.popPose();
        }
    }
}
