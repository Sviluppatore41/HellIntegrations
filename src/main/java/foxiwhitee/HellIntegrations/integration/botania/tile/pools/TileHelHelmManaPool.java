package foxiwhitee.HellIntegrations.integration.botania.tile.pools;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileHelHelmManaPool extends TileCustomManaPool{
    @Override
    public int getMaxMana() {
        return HellConfig.manaHelhelmPool;
    }

    @Override
    public String getName() {
        return BotaniaIntegration.HELHELM_POOL.getUnlocalizedName().replace("tile.", "");
    }
}
