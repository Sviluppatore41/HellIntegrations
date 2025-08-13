package foxiwhitee.HellIntegrations.blocks;

import appeng.block.AEBaseBlock;
import net.minecraft.block.material.Material;
import foxiwhitee.HellIntegrations.HellCore;

public class BaseBlock extends AEBaseBlock {
    public BaseBlock(String name) {
        super(Material.rock);
        this.setBlockName(name);
        this.setLightLevel(2f);
        this.setLightOpacity(10);
        this.setCreativeTab(HellCore.HELL_TAB);
        this.setHardness(3);
        this.setResistance(10);
        this.setHarvestLevel("pickaxe", 3);
        this.setStepSound(soundTypeMetal);
        this.setBlockTextureName(HellCore.MODID.toLowerCase()+":"+name);
    }
}