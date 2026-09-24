package fuzs.tinyskeletons.neoforge;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import fuzs.tinyskeletons.common.TinySkeletons;
import fuzs.tinyskeletons.common.data.loot.ModEntityTypeLootProvider;
import fuzs.tinyskeletons.common.data.tags.ModEntityTypeTagProvider;
import fuzs.tinyskeletons.common.data.tags.ModItemTagProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(TinySkeletons.MOD_ID)
public class TinySkeletonsNeoForge {

    public TinySkeletonsNeoForge() {
        ModConstructor.construct(TinySkeletons.MOD_ID, TinySkeletons::new);
        DataProviderBuilder.of(TinySkeletons.MOD_ID)
                .addLootProvider(ModEntityTypeLootProvider::new, LootContextParamSets.ENTITY)
                .addProvider(ModEntityTypeTagProvider::new, ModItemTagProvider::new);
    }
}
