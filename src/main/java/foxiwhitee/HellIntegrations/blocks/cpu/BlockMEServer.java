package foxiwhitee.HellIntegrations.blocks.cpu;


import appeng.block.AEBaseTileBlock;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.client.gui.GuiMEServer;
import foxiwhitee.HellIntegrations.container.ContainerMEServer;
import foxiwhitee.HellIntegrations.tile.cpu.TileMEServer;
import foxiwhitee.HellIntegrations.utils.handler.GuiHandlers;
import foxiwhitee.HellIntegrations.utils.handler.SimpleGuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SimpleGuiHandler(index = GuiHandlers.meServer, tile = TileMEServer.class, gui = GuiMEServer.class, container = ContainerMEServer.class)
public class BlockMEServer extends AEBaseTileBlock {
    public BlockMEServer(String name) {
        super(Material.iron);
        this.setTileEntity( TileMEServer.class );
        this.setBlockName(name);
        this.setCreativeTab(HellCore.HELL_TAB);
        this.setBlockTextureName(HellCore.MODID.toLowerCase() + ":ae2/" + name);
    }

    public boolean onActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileMEServer)
            FMLNetworkHandler.openGui(player, HellCore.instance, GuiHandlers.meServer, world, x, y, z);
        return true;
    }
}
