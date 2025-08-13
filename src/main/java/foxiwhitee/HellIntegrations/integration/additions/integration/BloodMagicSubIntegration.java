package foxiwhitee.HellIntegrations.integration.additions.integration;

import appeng.api.AEApi;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import foxiwhitee.additions.ModItems;
import foxiwhitee.HellIntegrations.integration.IIntegration;
import foxiwhitee.HellIntegrations.integration.Integration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static foxiwhitee.HellIntegrations.ModRecipes.registerAutoPressRecipe;


@Integration(modid = "HellAdditions", dependencies = {"AWWayofTime"})
public class BloodMagicSubIntegration implements IIntegration {

    public void preInit(FMLPreInitializationEvent e) {}

    public void init(FMLInitializationEvent e) {
        if (ModItems.ITEMS.containsKey("AE_CPU_Blood") && ModItems.ITEMS.containsKey("AEContBlood")) {
            registerAutoPressRecipe(new ItemStack(ModItems.ITEMS.get("AE_CPU_Blood")),
                    new ItemStack(ModItems.ITEMS.get("AEContBlood")),
                    AEApi.instance().definitions().materials().siliconPrint().maybeStack(1).get(),
                    new ItemStack(Items.redstone));
            registerAutoPressRecipe(new ItemStack(ModItems.ITEMS.get("AEContBlood")),
                    new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        }
    }

    public void postInit(FMLPostInitializationEvent e) {}
}
