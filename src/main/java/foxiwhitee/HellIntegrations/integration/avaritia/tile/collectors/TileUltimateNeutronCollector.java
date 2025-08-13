package foxiwhitee.HellIntegrations.integration.avaritia.tile.collectors;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.avaritia.AvaritiaIntegration;
import foxiwhitee.HellIntegrations.integration.avaritia.utils.NeutronCollectorsOutput;
import net.minecraft.item.ItemStack;

public class TileUltimateNeutronCollector extends TileCustomNeutronCollector{
    @Override
    public String getName() {
        return AvaritiaIntegration.ULTIMATE_NEUTRON_COLLECTOR.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    public int getTicks() {
        return HellConfig.ultTicks;
    }

    @Override
    public ItemStack getStack() {
        return NeutronCollectorsOutput.ULTIMATE.getStack();
    }
}
