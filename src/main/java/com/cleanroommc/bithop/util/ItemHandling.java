package com.cleanroommc.bithop.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class ItemHandling {

    /*public static int getFirstTransferrableSlotCap(IItemHandler from, IItemHandler to) {
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
    }*/

    @Nonnull
    public static ItemStack insertItemStacked(IItemHandler inventory, @Nonnull ItemStack stack, boolean simulate) {
        if (inventory == null || stack.isEmpty()) return stack;

        // not stackable -> just insert into a new slot
        if (!stack.isStackable()) {
            return ItemHandlerHelper.insertItem(inventory, stack, simulate);
        }

        int sizeInventory = inventory.getSlots();

        // go through the inventory and try to fill up already existing items
        for (int i = 0; i < sizeInventory; i++) {
            ItemStack slot = inventory.getStackInSlot(i);
            if (ItemHandlerHelper.canItemStacksStack(slot, stack)) {
                stack = inventory.insertItem(i, stack, simulate);
                if (stack.isEmpty()) {
                    break;
                }
            }
        }
        return stack.isEmpty() ? ItemStack.EMPTY : ItemHandlerHelper.insertItem(inventory, stack, simulate);
    }

    public static void handleTransfer(@Nullable IItemHandler from, @Nullable IItemHandler to) {
        if (from == null || to == null) return;
        for (int i = 0; i < from.getSlots(); i++) {
            ItemStack itemExtract = from.extractItem(i, 1, true);
            if (itemExtract.isEmpty()) continue;
            ItemStack remainder = insertItemStacked(to, itemExtract, false);
            if (remainder.isEmpty()) {
                from.extractItem(i, 1, false);
                return;
            }
        }
    }
}
