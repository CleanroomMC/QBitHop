package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityPullHop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockPullHop extends BlockHopTe<TileEntityPullHop> {

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
}
