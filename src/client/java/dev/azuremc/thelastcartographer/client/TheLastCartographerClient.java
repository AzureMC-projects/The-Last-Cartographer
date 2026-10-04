package dev.azuremc.thelastcartographer.client;

import dev.azuremc.thelastcartographer.TheLastCartographer;
import net.fabricmc.api.ClientModInitializer;

public final class TheLastCartographerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TheLastCartographer.LOGGER.info("Client systems initialized.");
    }
}
