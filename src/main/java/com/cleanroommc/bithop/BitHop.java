package com.cleanroommc.bithop;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.12.2]", dependencies = "required-after:modularui@[2.5.0-rc5,3.0.0)")
public class BitHop {

    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    public static final BitHopTab creativeTab = new BitHopTab();

    @Mod.Instance(Tags.MODID) public static BitHop instance;

    @SidedProxy(modId = Tags.MODID,
                clientSide = "com.cleanroommc.bithop.ClientProxy",
                serverSide = "com.cleanroommc.bithop.CommonProxy") public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }
}
