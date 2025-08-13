package foxiwhitee.HellIntegrations.integration.botania.tile.pools;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;

public class TileMidgardManaPool extends TileCustomManaPool{
    @Override
    public int getMaxMana() {
        return HellConfig.manaMidgardPool;
    }

    @Override
    public String getName() {
        return BotaniaIntegration.MIDGARD_POOL.getUnlocalizedName().replace("tile.", "");
    }
}
