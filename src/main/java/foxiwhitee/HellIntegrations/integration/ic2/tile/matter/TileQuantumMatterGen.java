package foxiwhitee.HellIntegrations.integration.ic2.tile.matter;

import foxiwhitee.HellIntegrations.config.HellConfig;
import net.minecraftforge.fluids.FluidTank;

public class TileQuantumMatterGen extends TileCustomMatterGen{
    protected final FluidTank fluidTank;
    public TileQuantumMatterGen() {
        this.fluidTank = new FluidTank(HellConfig.quantumMatterTank);
    }
    @Override
    public String getName() {
        return "quantum_matter";
    }

    @Override
    public int getMatter() {
        return HellConfig.quantumMatterGeneration;
    }

    @Override
    public FluidTank getFluidTank() {
        return this.fluidTank;
    }
}
