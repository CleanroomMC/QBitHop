package com.cleanroommc.bithop.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

public class Vec3 {

    public double x, y, z;

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double choose(EnumFacing facing) {
        return choose(facing.getAxis());
    }

    public double choose(EnumFacing.Axis axis) {
        if (axis == EnumFacing.Axis.X) return this.x;
        if (axis == EnumFacing.Axis.Y) return this.y;
        if (axis == EnumFacing.Axis.Z) return this.z;
        return 0;
    }

    public void chooseSet(EnumFacing facing, double v) {
        chooseSet(facing.getAxis(), v);
    }

    public void chooseSet(EnumFacing.Axis axis, double v) {
        if (axis == EnumFacing.Axis.X) this.x = v;
        else if (axis == EnumFacing.Axis.Y) this.y = v;
        else if (axis == EnumFacing.Axis.Z) this.z = v;
    }

    public void setFromStart(AxisAlignedBB aabb) {
        set(aabb.minX, aabb.minY, aabb.minZ);
    }

    public void setFromEnd(AxisAlignedBB aabb) {
        set(aabb.maxX, aabb.maxY, aabb.maxZ);
    }

    public void transform(EnumFacing from, EnumFacing to) {
        transform(from.getAxis(), to.getAxis());
    }

    public void transform(EnumFacing.Axis from, EnumFacing.Axis to) {
        double temp = choose(from);
        chooseSet(from, choose(to));
        chooseSet(to, temp);
    }

    public void chooseOneMinus(EnumFacing facing) {
        chooseSet(facing.getAxis(), 1 - choose(facing.getAxis()));
    }
}
