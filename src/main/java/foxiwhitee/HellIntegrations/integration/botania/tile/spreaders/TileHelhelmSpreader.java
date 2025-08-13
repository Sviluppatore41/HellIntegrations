package foxiwhitee.HellIntegrations.integration.botania.tile.spreaders;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileHelhelmSpreader extends TileCustomSpreader{
    @Override
    public String getName() {
        return BotaniaIntegration.HELHELM_SPREADER.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    public int getManaPerSec() {
        return HellConfig.manaPerSecHelhelmSpreader;
    }
}
