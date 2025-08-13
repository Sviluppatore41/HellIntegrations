package foxiwhitee.HellIntegrations.integration.botania.items.patterns;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import foxiwhitee.HellIntegrations.helpers.UniversalPatternHelper;
import foxiwhitee.HellIntegrations.integration.botania.items.ae.ItemManaDrop;
import foxiwhitee.HellIntegrations.integration.botania.recipes.CustomRecipeManaInfusion;
import foxiwhitee.HellIntegrations.items.patterns.ItemUniversalEncodedPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;

public class ItemEncodedManaPoolPattern extends ItemUniversalEncodedPattern {

    public ItemEncodedManaPoolPattern(String name) {
        super(name);
    }

    public ICraftingPatternDetails getPatternForItem(ItemStack is, World w) {
        try {
            return new UniversalPatternHelper(is, false, 2, 1, matrix -> {
                ItemStack itemstack = matrix.getStackInSlot(0);
                if (itemstack.getItem() instanceof ItemManaDrop) {
                    itemstack = matrix.getStackInSlot(1);
                }

                if (itemstack == null) {
                    return null;
                }

                CustomRecipeManaInfusion recipe;

                for (int i = 0; i < BotaniaAPI.manaInfusionRecipes.size(); i++) {
                    recipe = new CustomRecipeManaInfusion(BotaniaAPI.manaInfusionRecipes.get(i));
                    if (recipe.matches(itemstack)) {
                        return recipe;
                    }
                }
                return null;
            });
        } catch (Throwable var4) {
            return null;
        }
    }


}
