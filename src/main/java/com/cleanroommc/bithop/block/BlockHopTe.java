package com.cleanroommc.bithop.block;

import com.cleanroommc.modularui.factory.TileEntityGuiFactory;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class BlockHopTe<TE extends TileEntity> extends BlockTileEntity<TE> {

    public static EnumFacing getFacing(int meta) {
        return EnumFacing.byIndex(meta & 7);
    }

    private PropertyDirection facing;

    public BlockHopTe(String name) {
        this(Material.IRON, name);
    }

    public BlockHopTe(Material material, String name) {
        super(material, name);
    }

    public PropertyDirection getFacing() {
        return facing;
    }

    public boolean isValidFacing(@NotNull EnumFacing facing) {
        return facing != EnumFacing.UP;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        facing = PropertyDirection.create("facing", this::isValidFacing);
        return new BlockStateContainer(this, facing);
    }

    @Override
    public boolean onBlockActivated(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state,
                                    @NotNull EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX,
                                    float hitY, float hitZ) {
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            TileEntityGuiFactory.INSTANCE.open(playerIn, pos);
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public int getMetaFromState(@NotNull IBlockState state) {
        return getFacing(state).getIndex();
    }

    public @NotNull EnumFacing getFacing(@NotNull IBlockState state) {
        return state.getValue(facing);
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(facing, getFacing(meta));
    }

    @Override
    public @NotNull IBlockState getStateForPlacement(@NotNull World world, @NotNull BlockPos pos, @NotNull EnumFacing facing, float hitX,
                                                     float hitY, float hitZ, int meta, @NotNull EntityLivingBase placer,
                                                     @NotNull EnumHand hand) {
        facing = facing.getOpposite();
        if (facing == EnumFacing.UP) {
            facing = EnumFacing.DOWN;
        }
        return getDefaultState().withProperty(this.facing, facing);
    }
}
