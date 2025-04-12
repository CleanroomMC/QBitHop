package com.cleanroommc.bithop.item;


import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static ItemBase[] allItems = {};

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(allItems);
    }

    public static void registerModels() {
        for (ItemBase allItem : allItems) {
            allItem.registerItemModel();
        }
    }

    public static void registerOreDict() {
        for (ItemBase allItem : allItems) {
            allItem.initOreDict();
        }
    }
}
