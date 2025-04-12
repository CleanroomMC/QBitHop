package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityScrewHop;
import com.cleanroommc.bithop.util.AABB;
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

import java.util.List;

public class BlockScrewHop extends BlockHopTe<TileEntityScrewHop> {

    public static final AxisAlignedBB BASE_S = AABB.make(4, 0, 0, 12, 8, 7);
    public static final AxisAlignedBB SHAFT_1S = AABB.make(6, 4, 4, 10, 10, 10);
    public static final AxisAlignedBB SHAFT_2S = AABB.make(6, 10, 10, 10, 16, 16);
    public static final AxisAlignedBB FULL_N = AABB.make(4, 0, 0, 12, 16, 16);
    public static final AxisAlignedBB FULL_E = AABB.transform(FULL_N, EnumFacing.NORTH, EnumFacing.EAST);

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

    @Override
    protected void buildBounds(EnumFacing facing, List<AxisAlignedBB> bounds) {
        bounds.add(AABB.transform(BASE_S, EnumFacing.SOUTH, facing));
        bounds.add(AABB.transform(SHAFT_1S, EnumFacing.SOUTH, facing));
        bounds.add(AABB.transform(SHAFT_2S, EnumFacing.SOUTH, facing));
    }

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return switch (state.getValue(getFacing()).getAxis()) {
            case X, Y -> FULL_E;
            case Z -> FULL_N;
        };
    }

}
