package dev.azuremc.thelastcartographer;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import java.util.function.Function;

public final class ModItems {
    public static final ResourceKey<Item> CARTOGRAPHERS_JOURNAL_KEY = createKey("cartographers_journal");
    public static final Item CARTOGRAPHERS_JOURNAL = register(
            CARTOGRAPHERS_JOURNAL_KEY,
            Item::new,
            new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)
    );

    private ModItems() {}

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(creativeTab -> creativeTab.accept(CARTOGRAPHERS_JOURNAL));
        TheLastCartographer.LOGGER.info("Registered Cartographer's Journal.");
    }

    private static ResourceKey<Item> createKey(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(TheLastCartographer.MOD_ID, name)
        );
    }

    private static <T extends Item> T register(
            ResourceKey<Item> key,
            Function<Item.Properties, T> factory,
            Item.Properties properties
    ) {
        T item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
}
