package foxiwhitee.HellIntegrations.integration.nei.botania;

import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.IRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import foxiwhitee.HellIntegrations.integration.botania.client.gui.*;
import foxiwhitee.HellIntegrations.network.NetworkManager;
import foxiwhitee.HellIntegrations.network.packets.PacketTerminalManaPoolRecipe;
import foxiwhitee.HellIntegrations.network.packets.PacketTerminalRecipes;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.integration.nei.recipe.*;
import vazkii.botania.common.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class BotaniaRecipeOverlayHandler implements IOverlayHandler {

    @Override
    public void overlayRecipe(@Nullable GuiContainer gui, @Nullable IRecipeHandler handler, int recipeIndex, boolean shift) {
        if (handler == null) return;

        NBTTagCompound data = new NBTTagCompound();
        List<PositionedStack> ingredients = handler.getIngredientStacks(recipeIndex);

        if (handler instanceof RecipeHandlerManaPool && gui instanceof GuiPartManaPoolPatternTerminal) {
            for (PositionedStack stack : ingredients) {
                if (stack.contains(new ItemStack(ModBlocks.pool))) {
                    ingredients.remove(stack);
                    break;
                }
            }
            processManaPoolRecipe(data, ingredients, recipeIndex, handler);
            if (!data.hasNoTags()) {
                NetworkManager.instance.sendToServer(new PacketTerminalManaPoolRecipe(data));
                return;
            }
        } else if (handler instanceof RecipeHandlerPureDaisy && gui instanceof GuiPartPureDaisyPatternTerminal) {
            for (PositionedStack stack : ingredients) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setString("type", "puredaisy");
                ItemStack itemStack = new ItemStack(ModBlocks.specialFlower);
                itemStack.setTagCompound(tagCompound);
                if (stack.contains(itemStack)) {
                    ingredients.remove(stack);
                    break;
                }
            }
            processSimpleRecipe(data, ingredients);
        } else if (handler instanceof RecipeHandlerPetalApothecary && !(handler instanceof RecipeHandlerRunicAltar) && gui instanceof GuiPartPetalsPatternTerminal) {
            for (PositionedStack stack : ingredients) {
                if (stack.contains(new ItemStack(ModBlocks.altar))) {
                    ingredients.remove(stack);
                    break;
                }
            }
            processMultiItemRecipe(data, ingredients);
        } else if (handler instanceof RecipeHandlerElvenTrade && gui instanceof GuiPartElvenTradePatternTerminal) {
            for (PositionedStack stack : ingredients) {
                if (stack.contains(new ItemStack(ModBlocks.alfPortal))) {
                    ingredients.remove(stack);
                    break;
                }
            }
            processMultiItemRecipe(data, ingredients);
        } else if (handler instanceof RecipeHandlerRunicAltar && gui instanceof GuiPartRuneAltarPatternTerminal) {
            for (PositionedStack stack : ingredients) {
                if (stack.contains(new ItemStack(ModBlocks.runeAltar))) {
                    ingredients.remove(stack);
                    break;
                }
            }
            processMultiItemRecipe(data, ingredients);
        }

        if (!data.hasNoTags()) {
            NetworkManager.instance.sendToServer(new PacketTerminalRecipes(data));
        }
    }

    private void processManaPoolRecipe(NBTTagCompound data, List<PositionedStack> ingredients, int recipeIndex, IRecipeHandler handler) {
        List<NBTTagCompound> list = createItemTagList(ingredients.get(0).items);
        data.setTag("#0", list.isEmpty() ? new NBTTagCompound() : list.get(0));

        TemplateRecipeHandler.CachedRecipe recipe = ((RecipeHandlerManaPool) handler).arecipes.get(recipeIndex);
        if (recipe.getOtherStacks().size() == 1) {
            ItemStack catalyst = recipe.getOtherStacks().get(0).item;
            Item catalystItem = catalyst != null ? catalyst.getItem() : null;
            if (catalystItem == Item.getItemFromBlock(ModBlocks.alchemyCatalyst)) {
                data.setInteger("catalist", 1);
            } else if (catalystItem == Item.getItemFromBlock(ModBlocks.conjurationCatalyst)) {
                data.setInteger("catalist", 2);
            } else {
                data.setInteger("catalist", 0);
            }
        }
    }

    private void processSimpleRecipe(NBTTagCompound data, List<PositionedStack> ingredients) {
        List<NBTTagCompound> list = createItemTagList(ingredients.get(0).items);
        data.setTag("#0", list.isEmpty() ? new NBTTagCompound() : list.get(0));
    }

    private void processMultiItemRecipe(NBTTagCompound data, List<PositionedStack> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            List<NBTTagCompound> list = createItemTagList(ingredients.get(i).items);
            data.setTag("#" + i, list.isEmpty() ? new NBTTagCompound() : list.get(0));
        }
    }

    private List<NBTTagCompound> createItemTagList(ItemStack[] items) {
        List<NBTTagCompound> compounds = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null) {
                NBTTagCompound compound = new NBTTagCompound();
                item.writeToNBT(compound);
                compounds.add(compound);
            }
        }
        return compounds;
    }
}
