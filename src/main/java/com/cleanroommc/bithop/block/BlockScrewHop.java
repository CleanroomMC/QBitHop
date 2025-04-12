package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityScrewHop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockScrewHop extends BlockHopTe<TileEntityScrewHop> {

    private static final AxisAlignedBB x = new AxisAlignedBB(4 / 16.0, 0.0D, 0.0D, 12 / 16.0, 1.0D, 1.0D);
    private static final AxisAlignedBB z = new AxisAlignedBB(0.0D, 0.0D, 4 / 16.0, 1.0D, 1.0D, 12 / 16.0);

    public BlockScrewHop(String name) {
        super(name);
    }

    @Override
    public boolean isValidFacing(@NotNull EnumFacing facing) {
        return facing != EnumFacing.UP && facing != EnumFacing.DOWN;
    }

    @Override
    public Class<TileEntityScrewHop> getTileEntityClass() {
        return TileEntityScrewHop.class;
    }

    @Override
    public @Nullable TileEntityScrewHop createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEntityScrewHop();
    }

    @Override
    public @NotNull IBlockState getStateForPlacement(@NotNull World world, @NotNull BlockPos pos, @NotNull EnumFacing facing, float hitX,
                                                     float hitY, float hitZ, int meta, @NotNull EntityLivingBase placer,
                                                     @NotNull EnumHand hand) {
        EnumFacing enumfacing = facing.getOpposite();

        if (enumfacing == EnumFacing.UP || enumfacing == EnumFacing.DOWN) {
            enumfacing = placer.getHorizontalFacing();
        }

        return this.getDefaultState().withProperty(getFacing(), enumfacing);
    }

    /*@Override
    public void breakBlock(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntityScrewHop tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for(int i=0;i<5;i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
        super.breakBlock(world, pos, state);
    }*/

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return switch (state.getValue(getFacing()).getAxis()) {
            case X, Y -> x;
            case Z -> z;
        };
    }

}
