package foxiwhitee.hellmod.integration.botania.blocks;

import appeng.block.AEBaseBlock;
import foxiwhitee.hellmod.HellCore;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;
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
        setBlockTextureName(HellCore.MODID + ":botania/" + name);
        setCreativeTab(HellCore.HELL_TAB);
    }
}
