package fuzs.tinyskeletons.common.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

public class BabySkeletonRenderState extends SkeletonRenderState {

    public ItemStackRenderState getOffHandItemState() {
        return this.mainArm != HumanoidArm.RIGHT ? this.rightHandItemState : this.leftHandItemState;
    }

    public ItemStack getOffHandItemStack() {
        return this.mainArm != HumanoidArm.RIGHT ? this.rightHandItemStack : this.leftHandItemStack;
    }
}
