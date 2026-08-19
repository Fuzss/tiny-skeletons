package fuzs.tinyskeletons.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.world.entity.HumanoidArm;

/**
 * An adaptation of {@link ItemInHandLayer} that does not render the off-hand item, so it can be handled separately.
 */
public class ItemInMainHandLayer<S extends ArmedEntityRenderState, M extends EntityModel<S> & ArmedModel<S>> extends ItemInHandLayer<S, M> {

    public ItemInMainHandLayer(RenderLayerParent<S, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, S state, float yRot, float xRot) {
        if (state.mainArm == HumanoidArm.RIGHT) {
            this.submitArmWithItem(state,
                    state.rightHandItemState,
                    state.rightHandItemStack,
                    HumanoidArm.RIGHT,
                    poseStack,
                    submitNodeCollector,
                    packedLight);
        } else {
            this.submitArmWithItem(state,
                    state.leftHandItemState,
                    state.leftHandItemStack,
                    HumanoidArm.LEFT,
                    poseStack,
                    submitNodeCollector,
                    packedLight);
        }
    }
}
