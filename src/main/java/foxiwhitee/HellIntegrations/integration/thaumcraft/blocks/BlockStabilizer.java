package foxiwhitee.HellIntegrations.integration.thaumcraft.blocks;

import appeng.block.AEBaseBlock;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.integration.thaumcraft.tile.TileStabilizer;
import foxiwhitee.HellIntegrations.utils.helpers.RenderIDs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;

public class BlockStabilizer extends AEBaseBlock implements ITileEntityProvider, IInfusionStabiliser {

    public BlockStabilizer(String name) {
        super(Material.iron);
        this.setBlockName(name);
        this.setCreativeTab(HellCore.HELL_TAB);
        isOpaque = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileStabilizer();
    }

    public int getRenderType() {
        return RenderIDs.STABILIZER.getId();
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean canStabaliseInfusion(World world, int i, int i1, int i2) {
        return true;
    }
}

