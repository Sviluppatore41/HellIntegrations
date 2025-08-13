package foxiwhitee.HellIntegrations.helpers;

import foxiwhitee.HellIntegrations.recipes.IHellRecipe;
import net.minecraft.inventory.InventoryCrafting;

@FunctionalInterface
public interface IFindMatchingRecipe {
    IHellRecipe findMatchingRecipe(InventoryCrafting matrix);
}
