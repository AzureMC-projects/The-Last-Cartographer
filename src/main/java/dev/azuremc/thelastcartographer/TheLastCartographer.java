package dev.azuremc.thelastcartographer;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TheLastCartographer implements ModInitializer {
    public static final String MOD_ID = "the_last_cartographer";
    public static final String MOD_NAME = "The Last Cartographer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        CartographerConfig.load();
        ModItems.initialize();
        LOGGER.info("{} initialized for Minecraft 26.3.", MOD_NAME);
    }
}
