package foxiwhitee.HellIntegrations.blocks;


import appeng.block.AEBaseTileBlock;
import foxiwhitee.HellIntegrations.HellCore;
import foxiwhitee.HellIntegrations.tile.TileAutoCrystallizer;
import net.minecraft.block.material.Material;

public class BlockAutoCrystallizer extends AEBaseTileBlock {
    public BlockAutoCrystallizer(String name) {
        super(Material.iron);
        this.setTileEntity( TileAutoCrystallizer.class );
        this.setBlockName(name);
        this.setCreativeTab(HellCore.HELL_TAB);
        this.setBlockTextureName(HellCore.MODID.toLowerCase() + ":ae2/" + name);
    }
}
