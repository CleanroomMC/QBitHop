package com.cleanroommc.bithop.item;

import com.cleanroommc.bithop.BitHop;
import com.cleanroommc.bithop.BitHopConfig;
import com.cleanroommc.bithop.C28n;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBase extends Item {

    protected String name;
    private String oreName;

    public ItemBase(String name) {
        this.name = name;
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(BitHop.creativeTab);
    }

    public ItemBase(String name, int maxStack) {
        this.name = name;
        setTranslationKey(name);
        setRegistryName(name);
        this.maxStackSize = maxStack;
    }

    public ItemBase(String name, int maxStack, String oreDict) {
        this(name, maxStack);
        this.oreName = oreDict;
    }

    public ItemBase(String name, String oreDict) {
        this(name);
        this.oreName = oreDict;
    }

    public void registerItemModel() {
        BitHop.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public @NotNull ItemBase setCreativeTab(@NotNull CreativeTabs tab) {
        super.setCreativeTab(BitHop.creativeTab);
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@NotNull ItemStack stack, World worldIn, @NotNull List<String> tooltip, @NotNull ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            C28n.formatList(tooltip, "tooltip.bithop." + name, BitHopConfig.fluxHop.fluxHopTransfer);
        } else {
            C28n.formatList(tooltip, "preview.bithop." + name);
        }

    }

    public void initOreDict() {
        if (oreName == null) {
            return;
        }
        OreDictionary.registerOre(oreName, this);
    }
}
