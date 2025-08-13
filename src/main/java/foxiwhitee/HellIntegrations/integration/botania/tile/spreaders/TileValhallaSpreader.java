package foxiwhitee.HellIntegrations.integration.botania.tile.spreaders;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileValhallaSpreader extends TileCustomSpreader{
    @Override
    public String getName() {
        return BotaniaIntegration.VALHALLA_SPREADER.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    public int getManaPerSec() {
        return HellConfig.manaPerSecValhallaSpreader;
    }
}
