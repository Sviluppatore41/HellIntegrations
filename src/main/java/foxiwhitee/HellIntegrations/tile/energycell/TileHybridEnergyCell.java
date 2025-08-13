package foxiwhitee.HellIntegrations.tile.energycell;

import foxiwhitee.HellIntegrations.config.HellConfig;

public class TileHybridEnergyCell extends TileCustomEnergyCell{
    @Override
    protected double getInternalMaxPower() {
        return HellConfig.hybridEnergyCellPower;
    }
}
