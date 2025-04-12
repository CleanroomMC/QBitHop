package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityFluxHop;
import com.cleanroommc.bithop.util.AABB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockFluxHop extends BlockHopTe<TileEntityFluxHop> {

    public BlockFluxHop(String name) {
        super(name);
    }

    @Override
    public Class<TileEntityFluxHop> getTileEntityClass() {
        return TileEntityFluxHop.class;
    }

    @Override
    public @Nullable TileEntityFluxHop createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEntityFluxHop();
    }

    @Override
    protected void buildBounds(EnumFacing facing, List<AxisAlignedBB> bounds) {
        bounds.add(BlockPullHop.MIDDLE);
        bounds.add(BlockPullHop.TOP);
        if (facing == EnumFacing.DOWN) {
            bounds.add(AABB.make(6, 0, 6, 10, 4, 10));
        } else {
            bounds.add(AABB.transform(BlockPullHop.SIDE_NORTH, EnumFacing.NORTH, facing));
        }
    }
}
