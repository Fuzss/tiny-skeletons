package fuzs.tinyskeletons.common.client.model.monster.skeleton;

import fuzs.tinyskeletons.common.client.renderer.entity.state.BabyWitherSkeletonRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.util.Mth;

import java.util.Map;
import java.util.Set;

public class BabyWitherSkeletonModel extends BabySkeletonModel<BabyWitherSkeletonRenderState> {
    private static final Set<String> UPPER_BODY_PARTS = Set.of("head", "body", "waist", "right_arm", "left_arm");
    private static final Set<String> LEG_PARTS = Set.of("left_leg", "right_leg");
    private static final Set<String> FOOT_PARTS = Set.of("left_foot", "right_foot");

    public BabyWitherSkeletonModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return BabySkeletonModel.createBodyLayer(CubeDeformation.NONE, -6.25F, 6.0F, 5.0F);
    }

    public static ArmorModelSet<MeshDefinition> createBabyArmorMeshSet(CubeDeformation innerDeformation, CubeDeformation outerDeformation, PartPose armOffset) {
        return createArmorMeshSet((CubeDeformation cube) -> {
            return createBabyArmorMesh(cube, armOffset).apply((MeshDefinition mesh) -> {
                transformArmorPart(mesh.getRoot());
                return mesh;
            });
        }, BABY_ARMOR_PARTS_PER_SLOT, innerDeformation, outerDeformation);
    }

    private static void transformArmorPart(PartDefinition rootPart) {
        for (Map.Entry<String, PartDefinition> entry : rootPart.getChildren()) {
            PartDefinition childPart = entry.getValue();
            transformArmorPart(childPart);
            entry.setValue(childPart.transformed((PartPose pose) -> {
                if (UPPER_BODY_PARTS.contains(entry.getKey())) {
                    return pose.translated(0.0F, -2.0F, 0.0F);
                } else if (LEG_PARTS.contains(entry.getKey())) {
                    return pose.translated(0.0F, -1.0F, 0.0F);
                } else if (FOOT_PARTS.contains(entry.getKey())) {
                    return pose.translated(0.0F, 1.0F, 0.0F);
                } else {
                    return pose;
                }
            }));
        }
    }

    /**
     * @see net.minecraft.client.model.monster.piglin.PiglinModel#setupAnim(PiglinRenderState)
     */
    @Override
    public void setupAnim(BabyWitherSkeletonRenderState state) {
        super.setupAnim(state);
        if (state.hasSkullItem()) {
            float f = Mth.sin(state.attackTime * Mth.PI);
            float f1 = Mth.sin((1.0F - (1.0F - state.attackTime) * (1.0F - state.attackTime)) * Mth.PI);
            this.rightArm.zRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightArm.yRot = -(0.1F - f * 0.6F);
            this.leftArm.yRot = 0.1F - f * 0.6F;
            this.rightArm.xRot = -Mth.HALF_PI;
            this.leftArm.xRot = -Mth.HALF_PI;
            this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
            this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
            // There should be no call to bob arms.
        }

        if (state.isDancing) {
            float f3 = state.ageInTicks / 60.0F;
            this.head.x = Mth.sin(f3 * 10.0F);
            this.head.y = Mth.sin(f3 * 40.0F) + 0.4F;
            this.rightArm.zRot = (Mth.PI / 180F) * (70.0F + Mth.cos(f3 * 40.0F) * 10.0F);
            this.leftArm.zRot = this.rightArm.zRot * -1.0F;
            this.rightArm.y = Mth.sin(f3 * 40.0F) * 0.5F + 1.5F;
            this.leftArm.y = Mth.sin(f3 * 40.0F) * 0.5F + 1.5F;
            this.body.y = Mth.sin(f3 * 40.0F) * 0.35F;
        }
    }
}
