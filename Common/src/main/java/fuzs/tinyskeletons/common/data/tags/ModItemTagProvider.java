package fuzs.tinyskeletons.common.data.tags;

import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import fuzs.tinyskeletons.common.init.ModRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.world.item.Item;

public class ModItemTagProvider extends AbstractTagProvider<Item> {

    public ModItemTagProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ModRegistry.BABY_BOGGED_THROWABLES_ITEM_TAG)
                .add(BlockItemIds.BROWN_MUSHROOM.item(), BlockItemIds.RED_MUSHROOM.item());
        this.tag(ModRegistry.BABY_PARCHED_THROWABLES_ITEM_TAG).add(BlockItemIds.SAND.item());
        this.tag(ModRegistry.BABY_STRAY_THROWABLES_ITEM_TAG).add(ItemIds.SNOWBALL);
    }
}
