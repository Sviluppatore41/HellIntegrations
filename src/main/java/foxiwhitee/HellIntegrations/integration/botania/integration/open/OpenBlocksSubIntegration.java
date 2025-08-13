package foxiwhitee.HellIntegrations.integration.botania.integration.open;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import foxiwhitee.HellIntegrations.config.ContentConfig;
import foxiwhitee.HellIntegrations.integration.IIntegration;
import foxiwhitee.HellIntegrations.integration.Integration;
import foxiwhitee.HellIntegrations.integration.botania.integration.open.items.ItemGenerationFlowerCoresOpenBLocks;
import foxiwhitee.HellIntegrations.utils.helpers.RegisterUtils;
import net.minecraft.item.Item;

@Integration(modid = "Botania", dependencies = {"OpenBlocks"})
public class OpenBlocksSubIntegration implements IIntegration {

    public static Item GENERATION_FLOWER_CORES_OPEN_BLOCKS = new ItemGenerationFlowerCoresOpenBLocks("generationFlowerCoresOpenBlocks");

    public void preInit(FMLPreInitializationEvent e) {
        if (ContentConfig.enableManaGenerator && ContentConfig.enableMEMana && ContentConfig.enableMEFluid) {
            RegisterUtils.registerItem(GENERATION_FLOWER_CORES_OPEN_BLOCKS);
        }
    }

    public void init(FMLInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}

}
