package com.cleanroommc.bithop.util;

import com.cleanroommc.bithop.block.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class BitHopRecipes {

    public static void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {

        IForgeRegistry<IRecipe> r = event.getRegistry();

        // Crafting bench recipes

        recipe(r, new ShapedOreRecipe(new ResourceLocation("bithop:blocks"), new ItemStack(ModBlocks.BITHOP, 2), "ici", " i ", 'c',
                                      new ItemStack(Blocks.CHEST), 'i', new ItemStack(Items.IRON_INGOT)));
        recipe(r, new ShapedOreRecipe(new ResourceLocation("bithop:blocks"), new ItemStack(ModBlocks.FLUXHOP, 5), "iri", "ici", "rir", 'c',
                                      new ItemStack(Blocks.CHEST), 'i', new ItemStack(Items.IRON_INGOT), 'r',
                                      new ItemStack(Items.REDSTONE)));
        recipe(r, new ShapedOreRecipe(new ResourceLocation("bithop:blocks"), new ItemStack(ModBlocks.SCREWHOP, 1), "  i", "ii ", "ci ", 'c',
                                      new ItemStack(Blocks.CHEST), 'i', new ItemStack(Items.IRON_INGOT)));
        recipe(r, new ShapedOreRecipe(new ResourceLocation("bithop:blocks"), new ItemStack(ModBlocks.PULLHOP, 1), "ici", "i i", " i ", 'c',
                                      new ItemStack(Blocks.CHEST), 'i', new ItemStack(Items.IRON_INGOT)));
        recipe(r, new ShapedOreRecipe(new ResourceLocation("bithop:blocks"), new ItemStack(ModBlocks.STICKHOP, 1), "isi", "ici", "sis", 'c',
                                      new ItemStack(Blocks.CHEST), 'i', new ItemStack(Items.IRON_INGOT), 's',
                                      new ItemStack(Items.SLIME_BALL)));
    }

    public static <T extends IRecipe> void recipe(IForgeRegistry<IRecipe> registry, T t) {
        t.setRegistryName(
                new ResourceLocation(t.getRecipeOutput().getItem().getRegistryName() + "_" + t.getRecipeOutput().getItemDamage()));
        registry.register(t);
    }

}
