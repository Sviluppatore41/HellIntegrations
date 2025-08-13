package foxiwhitee.HellIntegrations.integration.additions;

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

@Integration(modid = "HellAdditions")
public class HellAdditionsIntegration implements IIntegration {

    public void preInit(FMLPreInitializationEvent e) {}

    public void init(FMLInitializationEvent e) {
        if (ModItems.ITEMS.containsKey("AE_CPU_Fluix") && ModItems.ITEMS.containsKey("AEContFluix")) {
            registerAutoPressRecipe(new ItemStack(ModItems.ITEMS.get("AE_CPU_Fluix")),
                    new ItemStack(ModItems.ITEMS.get("AEContFluix")),
                    AEApi.instance().definitions().materials().siliconPrint().maybeStack(1).get(),
                    new ItemStack(Items.redstone));
            registerAutoPressRecipe(new ItemStack(ModItems.ITEMS.get("AEContFluix")),
                    AEApi.instance().definitions().materials().fluixCrystal().maybeStack(1).get());
        }
    }

    public void postInit(FMLPostInitializationEvent e) {}
}
