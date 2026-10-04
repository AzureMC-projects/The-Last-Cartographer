package dev.azuremc.thelastcartographer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CartographerConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH =
            FabricLoader.getInstance().getConfigDir().resolve(TheLastCartographer.MOD_ID + ".json");
    private static Values values = new Values();

    private CartographerConfig() {}

    public static void load() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            if (Files.notExists(CONFIG_PATH)) {
                save();
                return;
            }
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                Values loaded = GSON.fromJson(reader, Values.class);
                values = loaded != null ? loaded.normalized() : new Values();
            }
            save();
        } catch (IOException | JsonParseException exception) {
            values = new Values();
            TheLastCartographer.LOGGER.error("Could not load {}. Using safe defaults.", CONFIG_PATH, exception);
            try {
                save();
            } catch (IOException saveException) {
                TheLastCartographer.LOGGER.error("Could not write default configuration to {}.", CONFIG_PATH, saveException);
            }
        }
    }

    public static Values values() {
        return values;
    }

    private static void save() throws IOException {
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(values.normalized(), writer);
        }
    }

    public static final class Values {
        public int discoveryFrequencySeconds = 20;
        public double landmarkRarityMultiplier = 1.0;
        public double mysteryEventFrequencyMultiplier = 1.0;
        public boolean npcSpawning = true;
        public boolean artifactGeneration = true;
        public boolean structureGeneration = true;
        public boolean dimensionAccess = true;
        public boolean discoveryRewards = true;
        public boolean sharedDiscoveries = false;

        private Values normalized() {
            discoveryFrequencySeconds = Math.max(1, Math.min(300, discoveryFrequencySeconds));
            landmarkRarityMultiplier = clamp(landmarkRarityMultiplier, 0.1, 10.0);
            mysteryEventFrequencyMultiplier = clamp(mysteryEventFrequencyMultiplier, 0.0, 10.0);
            return this;
        }

        private static double clamp(double value, double min, double max) {
            if (Double.isNaN(value) || Double.isInfinite(value)) return 1.0;
            return Math.max(min, Math.min(max, value));
        }
    }
}
