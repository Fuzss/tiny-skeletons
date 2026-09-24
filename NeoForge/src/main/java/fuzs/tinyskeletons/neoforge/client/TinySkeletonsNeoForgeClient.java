package fuzs.tinyskeletons.neoforge.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import fuzs.tinyskeletons.common.TinySkeletons;
import fuzs.tinyskeletons.common.client.TinySkeletonsClient;
import fuzs.tinyskeletons.common.data.client.ModLanguageProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = TinySkeletons.MOD_ID, dist = Dist.CLIENT)
public class TinySkeletonsNeoForgeClient {

    public TinySkeletonsNeoForgeClient() {
        ClientModConstructor.construct(TinySkeletons.MOD_ID, TinySkeletonsClient::new);
        DataProviderBuilder.of(TinySkeletons.MOD_ID).addProvider(ModLanguageProvider::new);
    }
}
