package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.BitHop;
import com.cleanroommc.bithop.BitHopConfig;
import com.cleanroommc.bithop.C28n;
import com.cleanroommc.bithop.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlockBase extends Block implements IBlockBase {

    protected String name;

    public BlockBase(Material material, String name) {
        super(material);
        this.name = name;
        setTranslationKey(name);
        setRegistryName(Tags.MODID, name);
        setCreativeTab(BitHop.creativeTab);

        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(1.4f);
        this.setResistance(8f); //actually quite susceptible to explosions
    }

    public String getFullTranslationKey() {
        return getTranslationKey() + ".name";
    }

    public void registerItemModel(Item itemBlock) {
        BitHop.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public @NotNull BlockBase setCreativeTab(@NotNull CreativeTabs tab) {
        super.setCreativeTab(tab);
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

    public Block toBlock() {
        return this;
    }
}
