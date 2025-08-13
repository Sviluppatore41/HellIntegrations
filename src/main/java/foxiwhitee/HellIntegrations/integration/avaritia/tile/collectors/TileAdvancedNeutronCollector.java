package foxiwhitee.HellIntegrations.integration.avaritia.tile.collectors;

import foxiwhitee.HellIntegrations.config.HellConfig;
import foxiwhitee.HellIntegrations.integration.avaritia.AvaritiaIntegration;
import foxiwhitee.HellIntegrations.integration.avaritia.utils.NeutronCollectorsOutput;
import net.minecraft.item.ItemStack;

public class TileAdvancedNeutronCollector extends TileCustomNeutronCollector{
    @Override
    public String getName() {
        return AvaritiaIntegration.ADVANCEDN_NEUTRON_COLLECTOR.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    public int getTicks() {
        return HellConfig.advTicks;
    }

    @Override
    public ItemStack getStack() {
        return NeutronCollectorsOutput.ADVANCED.getStack();
    }
}
