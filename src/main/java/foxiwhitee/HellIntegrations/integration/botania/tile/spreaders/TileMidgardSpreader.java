package foxiwhitee.HellIntegrations.integration.botania.tile.spreaders;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileMidgardSpreader extends TileCustomSpreader{
    @Override
    public String getName() {
        return BotaniaIntegration.MIDGARD_SPREADER.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    public int getManaPerSec() {
        return HellConfig.manaPerSecMidgardSpreader;
    }
}
