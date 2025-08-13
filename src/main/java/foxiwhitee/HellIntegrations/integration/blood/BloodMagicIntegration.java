package foxiwhitee.HellIntegrations.integration.blood;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.integration.IIntegration;
import foxiwhitee.HellIntegrations.integration.Integration;
import foxiwhitee.HellIntegrations.integration.blood.blocks.BlockIchorRune;
import foxiwhitee.HellIntegrations.integration.blood.blocks.BlockSingularRune;
import foxiwhitee.HellIntegrations.integration.blood.itemblock.ItemBlockRune;
import foxiwhitee.HellIntegrations.utils.helpers.RegisterUtils;
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
