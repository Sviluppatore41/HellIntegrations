package foxiwhitee.hellmod.integration.blood;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxiwhitee.hellmod.config.ContentConfig;
import foxiwhitee.hellmod.integration.IIntegration;
import foxiwhitee.hellmod.integration.Integration;
import foxiwhitee.hellmod.integration.blood.blocks.BlockIchorRune;
import foxiwhitee.hellmod.integration.blood.blocks.BlockSingularRune;
import foxiwhitee.hellmod.integration.blood.itemblock.ItemBlockRune;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.block.Block;

@Integration(modid = "AWWayofTime")
public class BloodMagicIntegration implements IIntegration {
    public static final Block ICHOR_RUNES = new BlockIchorRune("ichorRunes");
    public static final Block SINGULAR_RUNES = new BlockSingularRune("singularRunes");

    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableIchorRunes) {
            RegisterUtils.registerBlock(ICHOR_RUNES, ItemBlockRune.class);
        }
        if (ContentConfig.enableSingularRunes) {
            RegisterUtils.registerBlock(SINGULAR_RUNES, ItemBlockRune.class);
        }
    }

    public void init(FMLInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}
}
