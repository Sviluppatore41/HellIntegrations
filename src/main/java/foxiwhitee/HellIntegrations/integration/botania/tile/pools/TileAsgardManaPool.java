package foxiwhitee.HellIntegrations.integration.botania.tile.pools;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileAsgardManaPool extends TileCustomManaPool{
    @Override
    public int getMaxMana() {
        return HellConfig.manaAsgardPool;
    }

    @Override
    public String getName() {
        return BotaniaIntegration.ASGARD_POOL.getUnlocalizedName().replace("tile.", "");
    }
}
