package foxiwhitee.HellIntegrations.integration.botania.tile.pools;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileValhallaManaPool extends TileCustomManaPool{
    @Override
    public int getMaxMana() {
        return HellConfig.manaValhallaPool;
    }

    @Override
    public String getName() {
        return BotaniaIntegration.VALHALLA_POOL.getUnlocalizedName().replace("tile.", "");
    }
}
