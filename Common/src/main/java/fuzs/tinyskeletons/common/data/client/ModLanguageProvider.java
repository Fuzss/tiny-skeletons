package fuzs.tinyskeletons.common.data.client;

import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.tinyskeletons.common.init.ModRegistry;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.BABY_SKELETON_ENTITY_TYPE.value(), "Baby Skeleton");
        this.add(ModRegistry.BABY_WITHER_SKELETON_ENTITY_TYPE.value(), "Baby Wither Skeleton");
        this.add(ModRegistry.BABY_STRAY_ENTITY_TYPE.value(), "Baby Stray");
        this.add(ModRegistry.BABY_BOGGED_ENTITY_TYPE.value(), "Baby Bogged");
        this.add(ModRegistry.BABY_PARCHED_ENTITY_TYPE.value(), "Baby Parched");
        this.add(ModRegistry.THROWN_ITEM_ENTITY_TYPE.value(), "Thrown Item");
    }
}
