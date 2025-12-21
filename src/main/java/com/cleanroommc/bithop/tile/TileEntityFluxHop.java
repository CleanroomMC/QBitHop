package com.cleanroommc.bithop.tile;

import com.cleanroommc.bithop.BitHopConfig;
import com.cleanroommc.bithop.block.ModBlocks;
import com.cleanroommc.bithop.util.ObservableEnergyStorage;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityFluxHop extends TileEntityBaseHop {

    private final ObservableEnergyStorage energyStorage;

    public TileEntityFluxHop() {
        this.energyStorage = new ObservableEnergyStorage(10000, getEnergyTransferRate());
        this.energyStorage.listen(this::markDirty);
    }

    public int getEnergyTransferRate() {
        return getMaxCooldown() * BitHopConfig.fluxHop.fluxHopTransfer;
    }

    @Override
    public void activateHopper() {
        handlePush();
        handlePull();
        handleEnergy();
    }

    private void handleEnergy() {
        IEnergyStorage neighbor = getNeighborEnergyStorage(getFacing());
        if (neighbor == null) return;
        int extracted = this.energyStorage.extractEnergy(neighbor.getEnergyStored(), true);

        if (extracted != 0) {
            int qty = neighbor.receiveEnergy(extracted, false);
            if (qty > 0) this.energyStorage.extractEnergy(qty, false);
        }
    }

    public IEnergyStorage getNeighborEnergyStorage(EnumFacing facing) {
        TileEntity tile = getNeighbor(facing);
        return tile == null ? null : tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
    }

    @Override
    public String getTranslationKey() {
        return ModBlocks.FLUXHOP.getFullTranslationKey();
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        nbt.setTag("Energy", CapabilityEnergy.ENERGY.getStorage().writeNBT(CapabilityEnergy.ENERGY, this.energyStorage, null));
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTBase energy = compound.getTag("Energy");
        if (energy == null) energy = new NBTTagInt(0);
        CapabilityEnergy.ENERGY.getStorage().readNBT(CapabilityEnergy.ENERGY, this.energyStorage, null, energy);
    }

    @Override
    public <T> @Nullable T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }
        return super.getCapability(capability, facing);
    }
}
