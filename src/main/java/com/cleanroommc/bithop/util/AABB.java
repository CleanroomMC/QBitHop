package com.cleanroommc.bithop.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

public class AABB {

    private static final Vec3 A = new Vec3();
    private static final Vec3 B = new Vec3();
    private AABB() {}

    public static AxisAlignedBB make(int x0, int y0, int z0, int x1, int y1, int z1) {
        return new AxisAlignedBB(x0 / 16.0, y0 / 16.0, z0 / 16.0, x1 / 16.0, y1 / 16.0, z1 / 16.0);
    }

    public static double choose(EnumFacing axis, double x, double y, double z) {
        return Math.abs(axis.getXOffset() * x + axis.getYOffset() * y + axis.getZOffset() * z);
    }

    public static AxisAlignedBB of(Vec3 a, Vec3 b) {
        return new AxisAlignedBB(a.x, a.y, a.z, b.x, b.y, b.z);
    }

    public static AxisAlignedBB transform(AxisAlignedBB aabb, EnumFacing from, EnumFacing to) {
        if (from == to) return aabb;
        A.setFromStart(aabb);
        B.setFromEnd(aabb);
        if (from.getAxis() != to.getAxis()) {
            A.transform(from, to);
            B.transform(from, to);
        }
        if (from.getAxisDirection() != to.getAxisDirection()) {
            A.chooseOneMinus(to);
            B.chooseOneMinus(to);
        }
        return of(A, B);
    }
}
