package com.cleanroommc.bithop.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

// TODO this is garbage
public class ItemHandling {

    public static int getFirstTransferrableSlotCap(IItemHandler from, IItemHandler to) {
        for (int i = 0; i < from.getSlots(); i++) {
            if (!from.getStackInSlot(i).isEmpty() && canInsertExtract(from, to, i)) {
                return i;
            }
        }
        return -1;
    }

    public static int getFirstEmptySlotCap(IItemHandler cap, ItemStack test) {
        for (int i = 0; i < cap.getSlots(); i++) {
            if (cap.insertItem(i, test, true).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean canInsertExtract(IItemHandler from, IItemHandler to, int slot) {
        ItemStack itemExtract = from.extractItem(slot, 1, true);
        if (!itemExtract.isEmpty()) {
            int insertSlot = getFirstEmptySlotCap(to, itemExtract);
            if (insertSlot >= 0) {
                ItemStack insert = to.insertItem(insertSlot, itemExtract, true);
                if (insert.isEmpty()) return true;
            }
        }
        return false;
    }

    public static void handleTransfer(@Nullable IItemHandler from, @Nullable IItemHandler to) {
        if (from == null || to == null) return;
        int slotFull = getFirstTransferrableSlotCap(from, to);
        if (slotFull == -1) return;
        ItemStack itemExtract = from.extractItem(slotFull, 1, true);
        if (!itemExtract.isEmpty()) {
            int insertSlot = getFirstEmptySlotCap(to, itemExtract);
            if (insertSlot != -1) {
                ItemStack insert = to.insertItem(insertSlot, itemExtract, false);
                if (insert.isEmpty()) from.extractItem(slotFull, 1, false);
            }
        }
    }

}
