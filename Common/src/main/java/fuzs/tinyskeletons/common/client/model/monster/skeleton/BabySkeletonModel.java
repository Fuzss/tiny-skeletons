package fuzs.tinyskeletons.common.client.model.monster.skeleton;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

/**
 * @see net.minecraft.client.model.monster.zombie.BabyZombieModel
 */
public class BabySkeletonModel<S extends SkeletonRenderState> extends SkeletonModel<S> {

    public BabySkeletonModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return createBodyLayer(CubeDeformation.NONE, -6.25F);
    }

    public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
        return createBodyLayer(cubeDeformation, -6.15F);
    }

    private static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation, float headY) {
        return createBodyLayer(cubeDeformation, headY, 5.0F, 4.0F);
    }

    protected static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation, float headY, float upperBodyHeight, float lowerBodyHeight) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(8, 12)
                        .addBox(-2.0F, -2.5F, -1.0F, 4.0F, upperBodyHeight, 2.0F, cubeDeformation),
                PartPose.offset(0.0F, 24.0F - lowerBodyHeight - upperBodyHeight + 2.5F, 0.0F));
        PartDefinition head = root.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, headY, -3.0F, 6.0F, 6.0F, 6.0F, cubeDeformation),
                PartPose.offset(0.0F, 24.0F - lowerBodyHeight - upperBodyHeight + 0.25F, 0.0F));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(24, 13)
                        .addBox(-1.0F, -0.5F, -0.5F, 1.0F, upperBodyHeight, 1.0F, cubeDeformation),
                PartPose.offset(-2.0F, 24.0F - lowerBodyHeight - upperBodyHeight + 0.5F, 0.0F));
        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .texOffs(20, 13)
                        .addBox(-1.0F, -0.5F, -0.5F, 1.0F, upperBodyHeight, 1.0F, cubeDeformation),
                PartPose.offset(3.0F, 24.0F - lowerBodyHeight - upperBodyHeight + 0.5F, 0.0F));
        root.addOrReplaceChild("right_leg",
                CubeListBuilder.create()
                        .texOffs(4, 13)
                        .addBox(-1.0F, 0.0F, -0.5F, 1.0F, lowerBodyHeight, 1.0F, cubeDeformation),
                PartPose.offset(-0.5F, 24.0F - lowerBodyHeight, 0.0F));
        root.addOrReplaceChild("left_leg",
                CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-1.0F, 0.0F, -0.5F, 1.0F, lowerBodyHeight, 1.0F, cubeDeformation),
                PartPose.offset(1.5F, 24.0F - lowerBodyHeight, 0.0F));
        return LayerDefinition.create(mesh, 64, 32);
    }
}
