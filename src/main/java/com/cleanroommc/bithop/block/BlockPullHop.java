package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityPullHop;
import com.cleanroommc.bithop.util.AABB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockPullHop extends BlockHopTe<TileEntityPullHop> {

    public static final AxisAlignedBB TOP = AABB.make(0, 10, 0, 16, 16, 16);
    public static final AxisAlignedBB MIDDLE = AABB.make(4, 4, 4, 12, 10, 12);
    public static final AxisAlignedBB SIDE_NORTH = AABB.make(6, 4, 0, 10, 8, 4);

    public BlockPullHop(String name) {
        super(name);
    }

    @Override
    public Class<TileEntityPullHop> getTileEntityClass() {
        return TileEntityPullHop.class;
    }

    @Override
    public @Nullable TileEntityPullHop createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEntityPullHop();
    }

    @Override
    protected void buildBounds(EnumFacing facing, List<AxisAlignedBB> bounds) {
        bounds.add(MIDDLE);
        bounds.add(TOP);
        if (facing == EnumFacing.DOWN) {
            bounds.add(AABB.make(6, 0, 6, 10, 4, 10));
        } else {
            bounds.add(AABB.transform(SIDE_NORTH, EnumFacing.NORTH, facing));
        }
    }
}
