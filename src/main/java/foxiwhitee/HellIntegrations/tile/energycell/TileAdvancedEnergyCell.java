package foxiwhitee.HellIntegrations.tile.energycell;

import foxiwhitee.HellIntegrations.config.HellConfig;

public class TileAdvancedEnergyCell extends TileCustomEnergyCell{
    @Override
    protected double getInternalMaxPower() {
        return HellConfig.advancedEnergyCellPower;
    }
}
