package com.cleanroommc.bithop.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static final BlockBitHop BITHOP = new BlockBitHop();
    public static final BlockFluxHop FLUXHOP = new BlockFluxHop("fluxhop");
    public static final BlockScrewHop SCREWHOP = new BlockScrewHop("screwhop");
    public static final BlockPullHop PULLHOP = new BlockPullHop("pullhop");
    public static final BlockStickHop STICKHOP = new BlockStickHop("stickhop");

    public static BlockTileEntity<?>[] allBlocks = {
            BITHOP,
            FLUXHOP,
            SCREWHOP,
            PULLHOP,
            /*STICKHOP*/
    };

    public static void register(IForgeRegistry<Block> registry) {
        for (BlockTileEntity<?> block : allBlocks) {
            registry.register(block.toBlock());
            GameRegistry.registerTileEntity(block.getTileEntityClass(), block.getRegistryName().toString());
        }
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (IBlockBase block : allBlocks) {
            registry.register(block.createItemBlock());
        }

    }

    public static void registerModels() {
        for (IBlockBase block : allBlocks) {
            block.registerItemModel(Item.getItemFromBlock(block.toBlock()));
        }
    }
}
