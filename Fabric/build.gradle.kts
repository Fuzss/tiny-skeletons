plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-fabric")
}

dependencies {
    modApi(sharedLibs.fabricapi.fabric)
    modApi(sharedLibs.puzzleslib.fabric)
}

multiloader {
    modFile {
        json {
            customData.put("lithium:options", mapOf("mixin.world.chunk_access" to false))
        }
    }

    mixins {
        mixin("ChunkStatusTasksFabricMixin", "GenerationChunkHolderFabricMixin", "ServerChunkCacheFabricMixin")
    }
}
