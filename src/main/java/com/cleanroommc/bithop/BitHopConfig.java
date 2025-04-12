package com.cleanroommc.bithop;

import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MODID)
public class BitHopConfig {

    public static final FluxHop fluxHop = new FluxHop();

    public static class FluxHop {

        @Config.Comment("The RF/t for a FluxHop to transfer. Will transfer 8x this amount every 8 ticks.")
        @Config.RequiresWorldRestart
        public int fluxHopTransfer = 200;
    }
}
