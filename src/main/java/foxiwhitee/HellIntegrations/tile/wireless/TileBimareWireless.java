package foxiwhitee.HellIntegrations.tile.wireless;

import foxiwhitee.HellIntegrations.ModBlocks;
import foxiwhitee.HellIntegrations.config.HellConfig;
import net.minecraft.item.ItemStack;

public class TileBimareWireless extends TileCustomWireless{

    @Override
    public int getMaxChannelSize() {
        return HellConfig.wirelessBimareMaxChannelSize;
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(ModBlocks.WIRELESS_BIMARE);
    }
}
