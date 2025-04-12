package com.cleanroommc.bithop;

import com.cleanroommc.bithop.block.ModBlocks;
import com.cleanroommc.bithop.item.ModItems;
import com.cleanroommc.bithop.util.BitHopRecipes;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        BitHopRecipes.onRegisterRecipes(event);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.register(event.getRegistry());
        ModBlocks.registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.register(event.getRegistry());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModItems.registerOreDict();
    }

    public void registerItemRenderer(Item item, int meta, String id) {}

    public String i18nFormat(String key, Object[] format) {
        return I18n.format(key, format);
    }

    public boolean i18nContains(String key) {
        return I18n.hasKey(key);
    }
}
