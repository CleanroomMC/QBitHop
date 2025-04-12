package com.cleanroommc.bithop.tile;

import com.cleanroommc.bithop.block.ModBlocks;

public class TileEntityPullHop extends TileEntityBaseHop {

    @Override
    public void activateHopper() {
        handlePush();
        handlePull();
    }

    @Override
    public String getTranslationKey() {
        return ModBlocks.PULLHOP.getFullTranslationKey();
    }
}
