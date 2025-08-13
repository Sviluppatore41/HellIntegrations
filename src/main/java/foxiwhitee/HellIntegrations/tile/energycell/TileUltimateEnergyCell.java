package foxiwhitee.HellIntegrations.tile.energycell;

import foxiwhitee.HellIntegrations.config.HellConfig;

public class TileUltimateEnergyCell extends TileCustomEnergyCell{
    @Override
    protected double getInternalMaxPower() {
        return HellConfig.ultimateEnergyCellPower;
    }
}
