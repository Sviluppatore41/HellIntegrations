package foxiwhitee.HellIntegrations.integration.botania.blocks;

import appeng.block.AEBaseBlock;
import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.block.material.Material;

public class BlockBaseBotania extends AEBaseBlock {
    public String name;

    public BlockBaseBotania(String name) {
        this(name, Material.rock);
    }

    public BlockBaseBotania(String name, Material material) {
        super(material);
        this.name = name;
        setHardness(3.0F);
        setBlockName(name);
        setBlockTextureName(HellCore.MODID.toLowerCase() + ":botania/" + name);
        setCreativeTab(HellCore.HELL_TAB);
    }
}
