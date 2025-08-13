package foxiwhitee.HellIntegrations.integration.botania.items.patterns;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import foxiwhitee.HellIntegrations.helpers.UniversalPatternHelper;
import foxiwhitee.HellIntegrations.integration.botania.items.ae.ItemManaDrop;
import foxiwhitee.HellIntegrations.integration.botania.recipes.CustomRecipeElvenTrade;
import foxiwhitee.HellIntegrations.items.patterns.ItemUniversalEncodedPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;

import java.util.ArrayList;
import java.util.List;

public class ItemEncodedElvenTradePattern extends ItemUniversalEncodedPattern {
    public ItemEncodedElvenTradePattern(String name) {
        super(name);
    }

    @Override
    public ICraftingPatternDetails getPatternForItem(ItemStack is, World w) {
        try {
            return new UniversalPatternHelper(is, false, 4, 1, matrix -> {
                boolean minusMana = false;
                List<ItemStack> itemstacks = new ArrayList<>();
                ItemStack itemstack;
                for (int i = 0; i <  matrix.getSizeInventory(); i++) {
                    itemstack = matrix.getStackInSlot(i);
                    if (itemstack != null) {
                        if (itemstack.getItem() instanceof ItemManaDrop && !minusMana) {
                            minusMana = true;
                            continue;
                        }
                        itemstacks.add(itemstack.copy());
                    }
                }
                if (itemstacks.isEmpty()) {
                    return null;
                }
                for (ItemStack stack : itemstacks) {
                    if (stack == null) {
                        return null;
                    }
                }
                CustomRecipeElvenTrade recipe;

                for (int var7 = 0; var7 < BotaniaAPI.elvenTradeRecipes.size(); ++var7) {
                    recipe = new CustomRecipeElvenTrade(BotaniaAPI.elvenTradeRecipes.get(var7));
                    if (recipe.matches(itemstacks)) {
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
