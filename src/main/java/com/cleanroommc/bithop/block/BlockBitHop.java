package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityBitHop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockBitHop extends BlockHopTe<TileEntityBitHop> {

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
}
