package fuzs.tinyskeletons.common.client.renderer.entity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class BabyWitherSkeletonRenderState extends SkeletonRenderState {
    public final BlockModelRenderState skullModel = new BlockModelRenderState();
    public boolean isDancing;

    public boolean hasSkullItem() {
        return !this.skullModel.isEmpty();
    }
}
