package com.cleanroommc.bithop.block;

import com.cleanroommc.bithop.tile.TileEntityStickHop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockStickHop extends BlockHopTe<TileEntityStickHop> {

    public BlockStickHop(String name) {
        super(name);
    }

    @Override
    public Class<TileEntityStickHop> getTileEntityClass() {
        return TileEntityStickHop.class;
    }

    @Override
    public @Nullable TileEntityStickHop createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        return new TileEntityStickHop();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public @NotNull BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
