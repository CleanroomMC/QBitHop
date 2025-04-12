package com.cleanroommc.bithop.block;

import com.cleanroommc.modularui.factory.TileEntityGuiFactory;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockHopTe<TE extends TileEntity> extends BlockTileEntity<TE> {

    public static EnumFacing getFacing(int meta) {
        return EnumFacing.byIndex(meta & 7);
    }

    private PropertyDirection facing;
    private List<AxisAlignedBB>[] bounds;

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
        bounds = new List[6];
        for (EnumFacing facing : facing.getAllowedValues()) {
            bounds[facing.getIndex()] = new ArrayList<>();
            buildBounds(facing, bounds[facing.getIndex()]);
        }
        return new BlockStateContainer(this, facing);
    }

    protected void buildBounds(EnumFacing facing, List<AxisAlignedBB> bounds) {

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

    @Nullable
    @Override
    public RayTraceResult collisionRayTrace(IBlockState blockState, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Vec3d start,
                                            @NotNull Vec3d end) {
        List<AxisAlignedBB> bounds = this.bounds[blockState.getValue(facing).getIndex()];
        if (bounds == null || bounds.isEmpty()) return super.collisionRayTrace(blockState, worldIn, pos, start, end);
        for (AxisAlignedBB bound : bounds) {
            RayTraceResult rt = rayTrace(pos, start, end, bound);
            if (rt != null) {
                return rt;
            }
        }
        return null;
    }

    @Override
    public void addCollisionBoxToList(@NotNull IBlockState state, @NotNull World worldIn, @NotNull BlockPos pos,
                                      @NotNull AxisAlignedBB entityBox, @NotNull List<AxisAlignedBB> collidingBoxes,
                                      @Nullable Entity entityIn, boolean isActualState) {
        List<AxisAlignedBB> bounds = this.bounds[state.getValue(facing).getIndex()];
        if (bounds == null || bounds.isEmpty()) {
            super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, isActualState);
            return;
        }
        for (AxisAlignedBB bound : bounds) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, bound);
        }
    }
}
