package foxiwhitee.HellIntegrations.tile.wireless;

import foxiwhitee.HellIntegrations.ModBlocks;
import foxiwhitee.HellIntegrations.config.HellConfig;
import net.minecraft.item.ItemStack;

public class TileDefitWireless extends TileCustomWireless{

    @Override
    public int getMaxChannelSize() {
        return HellConfig.wirelessDefitMaxChannelSize;
    }

    @Override
    protected ItemStack getItemFromTile(Object obj) {
        return new ItemStack(ModBlocks.WIRELESS_DEFIT);
    }
}
