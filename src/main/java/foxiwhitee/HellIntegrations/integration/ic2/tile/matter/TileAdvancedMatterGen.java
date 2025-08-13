package foxiwhitee.HellIntegrations.integration.ic2.tile.matter;

import foxiwhitee.HellIntegrations.config.HellConfig;
import net.minecraftforge.fluids.FluidTank;

public class TileAdvancedMatterGen extends TileCustomMatterGen{
    protected final FluidTank fluidTank;
    public TileAdvancedMatterGen() {
        this.fluidTank = new FluidTank(HellConfig.advancedMatterTank);
    }
    @Override
    public String getName() {
        return "advanced_matter";
    }

    @Override
    public int getMatter() {
        return HellConfig.advancedMatterGeneration;
    }

    @Override
    public FluidTank getFluidTank() {
        return this.fluidTank;
    }
}
