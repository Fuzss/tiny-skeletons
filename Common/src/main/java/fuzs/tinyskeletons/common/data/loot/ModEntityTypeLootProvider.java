package fuzs.tinyskeletons.common.data.loot;

import fuzs.puzzleslib.common.api.data.v3.loot.AbstractEntityLootSubProvider;
import fuzs.tinyskeletons.common.init.ModRegistry;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModEntityTypeLootProvider extends AbstractEntityLootSubProvider {

    public ModEntityTypeLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.add(ModRegistry.BABY_SKELETON_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.ARROW)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModRegistry.BABY_WITHER_SKELETON_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.COAL)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(-1, 1)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModRegistry.BABY_STRAY_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(TagEntry.expandTag(this.items.getOrThrow(ModRegistry.BABY_STRAY_THROWABLES_ITEM_TAG))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BLUE_ICE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        this.add(ModRegistry.BABY_BOGGED_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(TagEntry.expandTag(this.items.getOrThrow(ModRegistry.BABY_BOGGED_THROWABLES_ITEM_TAG))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.RED_MUSHROOM_BLOCK))
                                .add(LootItem.lootTableItem(Items.BROWN_MUSHROOM_BLOCK))
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                        ContextFloatProviders.between(0.0F, 1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())));
        this.add(ModRegistry.BABY_PARCHED_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(TagEntry.expandTag(this.items.getOrThrow(ModRegistry.BABY_PARCHED_THROWABLES_ITEM_TAG))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
    }
}
