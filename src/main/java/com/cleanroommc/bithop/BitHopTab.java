package com.cleanroommc.bithop;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BitHopTab extends CreativeTabs {

    public BitHopTab() {
        super(Tags.MODID);
    }

    @Override
    public @NotNull ItemStack createIcon() {
        return new ItemStack(Blocks.HOPPER);
    }
}
