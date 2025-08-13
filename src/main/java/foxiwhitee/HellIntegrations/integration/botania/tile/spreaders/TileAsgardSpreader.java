package foxiwhitee.HellIntegrations.integration.botania.tile.spreaders;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileAsgardSpreader extends TileCustomSpreader{
    @Override
    public String getName() {
        return BotaniaIntegration.ASGARD_SPREADER.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    public int getManaPerSec() {
        return HellConfig.manaPerSecAsgardSpreader;
    }
}
