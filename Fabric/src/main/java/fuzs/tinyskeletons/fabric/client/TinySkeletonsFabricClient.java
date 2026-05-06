package fuzs.tinyskeletons.fabric.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.tinyskeletons.common.TinySkeletons;
import fuzs.tinyskeletons.common.client.TinySkeletonsClient;
import net.fabricmc.api.ClientModInitializer;

public class TinySkeletonsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(TinySkeletons.MOD_ID, TinySkeletonsClient::new);
    }
}
