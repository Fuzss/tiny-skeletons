package fuzs.tinyskeletons.common.client.model.monster.skeleton;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

/**
 * @see net.minecraft.client.model.monster.skeleton.BoggedModel
 */
public class BabyBoggedModel<S extends SkeletonRenderState> extends BabySkeletonModel<S> {

    public BabyBoggedModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return BabySkeletonModel.createBodyLayer().apply((MeshDefinition mesh) -> {
            PartDefinition root = mesh.getRoot();
            PartDefinition mushrooms = root.getChild("head")
                    .addOrReplaceChild("mushrooms", CubeListBuilder.create(), PartPose.ZERO);
            mushrooms.addOrReplaceChild("red_mushroom_1",
                    CubeListBuilder.create().texOffs(28, 12).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F),
                    PartPose.offsetAndRotation(2.0F, -6.25F, 2.0F, 0.0F, -0.7854F, 0.0F));
            mushrooms.addOrReplaceChild("red_mushroom_2",
                    CubeListBuilder.create().texOffs(28, 12).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F),
                    PartPose.offsetAndRotation(2.0F, -6.25F, 2.0F, 0.0F, -2.3562F, 0.0F));
            mushrooms.addOrReplaceChild("brown_mushroom_1",
                    CubeListBuilder.create().texOffs(28, 18).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F),
                    PartPose.offsetAndRotation(-2.0F, -6.25F, -2.0F, 0.0F, -0.7854F, 0.0F));
            mushrooms.addOrReplaceChild("brown_mushroom_2",
                    CubeListBuilder.create().texOffs(28, 18).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F),
                    PartPose.offsetAndRotation(-2.0F, -6.25F, -2.0F, 0.0F, -2.3562F, 0.0F));
            mushrooms.addOrReplaceChild("brown_mushroom_3",
                    CubeListBuilder.create().texOffs(28, 15).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F),
                    PartPose.offsetAndRotation(-1.0F, -1.25F, 3.0F, -1.5708F, 0.0F, 0.7854F));
            mushrooms.addOrReplaceChild("brown_mushroom_4",
                    CubeListBuilder.create().texOffs(28, 15).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F),
                    PartPose.offsetAndRotation(-1.0F, -1.25F, 3.0F, -1.5708F, 0.0F, 2.3562F));
            return mesh;
        });
    }

    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        return BabySkeletonModel.createBodyLayer(cubeDeformation);
    }
}
