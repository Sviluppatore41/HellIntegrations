package foxiwhitee.HellIntegrations.integration.draconic.blocks;

import appeng.block.AEBaseTileBlock;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.client.gui.GuiAutomatedUpgradeableBlock;
import foxiwhitee.HellIntegrations.container.ContainerAutomatedUpgradeableBlock;
import foxiwhitee.HellIntegrations.integration.draconic.tile.TileAutoAwakenedBlocks;
import foxiwhitee.HellIntegrations.tile.TileAutomatedUpgradeableBlock;
import foxiwhitee.HellIntegrations.utils.handler.GuiHandlers;
import foxiwhitee.HellIntegrations.utils.handler.SimpleGuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SimpleGuiHandler(index = GuiHandlers.automatedUpgradeableBlock, tile = TileAutomatedUpgradeableBlock.class, gui = GuiAutomatedUpgradeableBlock.class, container = ContainerAutomatedUpgradeableBlock.class, integration = "DraconicEvolution")
public class BlockAutoAwakenedBlocks extends AEBaseTileBlock {

    public BlockAutoAwakenedBlocks(String name) {
        super(Material.iron);
        this.setTileEntity( TileAutoAwakenedBlocks.class );
        this.setBlockName(name);
        this.setCreativeTab(HellCore.HELL_TAB);
        this.setBlockTextureName(HellCore.MODID.toLowerCase() + ":ae2/" + name);
    }

    @Override
    public boolean onActivated(World w, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tile = w.getTileEntity(x, y, z);
        if (tile instanceof TileAutoAwakenedBlocks)
            FMLNetworkHandler.openGui(player, HellCore.instance, GuiHandlers.automatedUpgradeableBlock, w, x, y, z);
        return true;
    }
}
