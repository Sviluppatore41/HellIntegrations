package foxiwhitee.HellIntegrations.integration.ic2.blocks;

import appeng.block.AEBaseTileBlock;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.integration.ic2.tile.TileEUProvider;
import foxiwhitee.HellIntegrations.utils.helpers.RenderIDs;
import net.minecraft.block.material.Material;

public class BlockEUProvider extends AEBaseTileBlock {

    public BlockEUProvider(String name) {
        super(Material.iron);
        this.isOpaque = false;
        setBlockName(name);
        setTileEntity(TileEUProvider.class);
        setCreativeTab(HellCore.HELL_TAB);
        setHardness(1.0F);
        this.lightOpacity = 1;
    }

    public int getRenderType() {
        return RenderIDs.EU_ENERGY_PROVIDER.getId();
    }

    public boolean isBlockNormalCube() {
        return false;
    }

}