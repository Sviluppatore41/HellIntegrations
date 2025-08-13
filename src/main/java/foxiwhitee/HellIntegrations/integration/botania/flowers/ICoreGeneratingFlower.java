package foxiwhitee.HellIntegrations.integration.botania.flowers;

import net.minecraft.item.ItemStack;

public interface ICoreGeneratingFlower {
   IGeneratingFlowerLogic getLogic(String name);
   String getName(ItemStack stack);
}
