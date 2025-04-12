package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityBitHop;
import com.cleanroommc.bithop.util.AABB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockBitHop extends BlockHopTe<TileEntityBitHop> {

    public static final AxisAlignedBB TOP = BlockPullHop.TOP.setMaxY(13 / 16D);

    public BlockBitHop() {
        super("bithop");
    }

    @Override
    public Class<TileEntityBitHop> getTileEntityClass() {
        return TileEntityBitHop.class;
    }

    @Override
    public @Nullable TileEntityBitHop createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEntityBitHop();
    }

    @Override
    protected void buildBounds(EnumFacing facing, List<AxisAlignedBB> bounds) {
        bounds.add(BlockPullHop.MIDDLE);
        bounds.add(TOP);
        if (facing == EnumFacing.DOWN) {
            bounds.add(AABB.make(6, 0, 6, 10, 4, 10));
        } else {
            bounds.add(AABB.transform(BlockPullHop.SIDE_NORTH, EnumFacing.NORTH, facing));
        }
    }
}
