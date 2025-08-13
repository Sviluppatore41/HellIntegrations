package foxiwhitee.HellIntegrations.integration.ic2.tile.generators.panels;

import foxiwhitee.HellIntegrations.config.HellConfig;

public class TileSolarPanelLevel7 extends TileCustomSolarPanel{
    @Override
    public int getGenNight() {
        return HellConfig.panel7GenNight;
    }

    @Override
    public int getGenDay() {
        return HellConfig.panel7GenDay;
    }

    @Override
    public int getMaxStorage() {
        return this.getGenDay() * 10;
    }

    @Override
    public int getProduction() {
        return this.getGenDay() * 2;
    }

    @Override
    public String getName() {
        return "panel7";
    }
}
