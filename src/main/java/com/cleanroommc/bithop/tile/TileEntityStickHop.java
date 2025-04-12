package com.cleanroommc.bithop.tile;

import com.cleanroommc.bithop.block.ModBlocks;
import com.cleanroommc.bithop.util.ItemHandling;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class TileEntityStickHop extends TileEntityBaseHop {

    @Override
    public void activateHopper() {
        handlePush();
        handlePull();
    }

    private int getFirstTransferrableSlotSticky(IItemHandler to) {
        for (int slot = 0; slot < to.getSlots(); slot++) {
            ItemStack stack = to.getStackInSlot(slot);
            if (!stack.isEmpty() && stack.getCount() > 1 && ItemHandling.canInsertExtract(getInventory(), to, slot)) {
                return slot;
            }
        }
        return -1;
    }

    @Override
    protected void handlePush() {
        IItemHandler itemHandler = getNeighborInventory(getFacing());
        if (itemHandler == null) return;
        int slotFull = getFirstTransferrableSlotSticky(itemHandler);
        if (slotFull == -1) return;
        ItemStack itemExtract = getInventory().extractItem(slotFull, 1, true);
        if (!itemExtract.isEmpty()) {
            int insertSlot = ItemHandling.getFirstEmptySlotCap(itemHandler, itemExtract);
            if (insertSlot != -1) {
                ItemStack insert = itemHandler.insertItem(insertSlot, itemExtract, false);
                if (insert.isEmpty()) getInventory().extractItem(slotFull, 1, false);
            }
        }
    }

    @Override
    public String getTranslationKey() {
        return ModBlocks.STICKHOP.getFullTranslationKey();
    }
}
