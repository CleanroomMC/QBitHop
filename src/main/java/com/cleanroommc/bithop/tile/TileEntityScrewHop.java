package com.cleanroommc.bithop.tile;

import com.cleanroommc.bithop.block.ModBlocks;
import com.cleanroommc.bithop.util.ItemHandling;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityScrewHop extends TileEntityBaseHop {

    @Override
    public void activateHopper() {
        TileEntity neighbor = getWorld().getTileEntity(getPos().offset(getFacing()).offset(EnumFacing.UP));
        if (neighbor == null) return;
        IItemHandler itemHandler = neighbor.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getFacing().getOpposite());
        if (itemHandler == null) return;
        ItemHandling.handleTransfer(getInventory(), itemHandler);
    }

    @Override
    public String getTranslationKey() {
        return ModBlocks.SCREWHOP.getFullTranslationKey();
    }
}
