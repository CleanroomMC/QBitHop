package com.cleanroommc.bithop.tile;

import com.cleanroommc.bithop.block.ModBlocks;

public class TileEntityBitHop extends TileEntityBaseHop {

    @Override
    public void activateHopper() {
        handlePush();
    }

    @Override
    public String getTranslationKey() {
        return ModBlocks.BITHOP.getFullTranslationKey();
    }
}
