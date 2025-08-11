package foxiwhitee.hellmod.integration.nei.thaumcraft;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;
import com.djgiannuzz.thaumcraftneiplugin.items.ItemAspect;
import foxiwhitee.hellmod.network.NetworkManager;
import foxiwhitee.hellmod.network.packets.PacketTerminalRecipes;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;
import java.util.stream.Collectors;

public class InfusionRecipeOverlayHandler implements IOverlayHandler {
    public void overlayRecipe(GuiContainer firstGui, IRecipeHandler recipe, int recipeIndex, boolean shift) {
        List<PositionedStack> items = recipe.getIngredientStacks(recipeIndex);
        Map<Integer, List<NBTTagCompound>> stacks = new HashMap<>();
        int count = 0;
        for (PositionedStack pStack : items) {
            if (pStack == null)
                continue;
            List<NBTTagCompound> nbts = Arrays.stream(pStack.items)
                    .filter(Objects::nonNull)
                    .filter(stack -> !(stack.getItem() instanceof ItemAspect))
                    .map(stack -> {
                        NBTTagCompound compound = new NBTTagCompound();
                        stack.writeToNBT(compound);
                        return compound;
                    })
                    .collect(Collectors.toList());
            stacks.put(count++, nbts);
        }
        NBTTagCompound tag = new NBTTagCompound();
        for (int i = 0; i < 17; i++) {
            List<NBTTagCompound> list = stacks.getOrDefault(i, Collections.emptyList());
            if (!list.isEmpty()) {
                tag.setTag("#" + i, list.get(0));
            } else {
                tag.setTag("#" + i, new NBTTagCompound());
            }
        }
        NetworkManager.instance.sendToServer(new PacketTerminalRecipes(tag));
    }
}
