package com.cleanroommc.bithop.tile;

import com.cleanroommc.bithop.block.BlockHopTe;
import com.cleanroommc.bithop.util.ItemHandling;
import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TileEntityBaseHop extends TileEntity implements IGuiHolder<PosGuiData>, ITickable {

    private final ItemStackHandler inventory;
    private int cooldown = 0;

    public TileEntityBaseHop() {
        this.inventory = new ItemStackHandler(getSlotAmount()) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
        this.cooldown = getMaxCooldown();
    }

    public abstract void activateHopper();

    public int getSlotAmount() {
        return 5;
    }

    public int getMaxCooldown() {
        return 8;
    }

    public @Nullable TileEntity getNeighbor(EnumFacing facing) {
        return world.getTileEntity(getPos().offset(facing));
    }

    public @Nullable IItemHandler getNeighborInventory(EnumFacing facing) {
        TileEntity tile = getNeighbor(facing);
        return tile == null ? null : tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());
    }

    protected void handlePush() {
        ItemHandling.handleTransfer(getInventory(), getNeighborInventory(getFacing()));
    }

    protected void handlePull() {
        ItemHandling.handleTransfer(getNeighborInventory(EnumFacing.UP), getInventory());
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {
            if (--this.cooldown < 0) {
                activateHopper();
                this.cooldown = getMaxCooldown();
            }
        }
    }

    public EnumFacing getFacing() {
        IBlockState state = getWorld().getBlockState(getPos());
        if (state.getBlock() instanceof BlockHopTe<?> blockHopTe) {
            return blockHopTe.getFacing(state);
        }
        throw new IllegalStateException();
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public abstract String getTranslationKey();

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        syncManager.registerSlotGroup("hopper", getSlotAmount());
        return new ModularPanel(getClass().getSimpleName()).width(176)
                .coverChildrenHeight()
                .padding(7)
                .child(Flow.column()
                               .coverChildren()
                               .debugName("1")
                               .child(Flow.column()
                                              .coverChildrenHeight()
                                              .debugName("2")
                                              .child(IKey.lang(getTranslationKey()).asWidget().leftRel(0f))
                                              .child(SlotGroupWidget.builder()
                                                             .row("IIIII")
                                                             .key('I', i -> new ItemSlot()
                                                                     .slot(new ModularSlot(getInventory(), i)
                                                                                   .slotGroup("hopper")))
                                                             .build()
                                                             .marginTop(4)
                                                             .marginBottom(7)
                                                             .alignX(0.5f)
                                                             .debugName("hopper_slots")))
                               .child(SlotGroupWidget.playerInventory(false)));
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        nbt.setTag("Inventory", this.inventory.serializeNBT());
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
    }

    @Override
    public @Nullable <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.inventory);
        }
        return super.getCapability(capability, facing);
    }
}
