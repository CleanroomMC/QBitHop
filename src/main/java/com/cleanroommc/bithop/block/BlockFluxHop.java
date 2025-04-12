package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityFluxHop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
}
