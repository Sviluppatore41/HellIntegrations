package foxiwhitee.HellIntegrations.integration.nei.avaritia;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;
import foxiwhitee.HellIntegrations.network.NetworkManager;
import foxiwhitee.HellIntegrations.network.packets.PacketTerminalRecipes;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;
import java.util.stream.Collectors;

public class ExtremeRecipeOverlayHandler implements IOverlayHandler {

    public void overlayRecipe(GuiContainer firstGui, IRecipeHandler recipe, int recipeIndex, boolean shift) {
        List<PositionedStack> items = recipe.getIngredientStacks(recipeIndex);
        Map<Integer, List<NBTTagCompound>> stacks = new HashMap<>();
        for (PositionedStack pStack : items) {
            if (pStack == null)
                continue;
            int x = pStack.relx / 18;
            int y = pStack.rely / 18;
            List<NBTTagCompound> nbts = (List<NBTTagCompound>)Arrays.<ItemStack>stream(pStack.items).filter(Objects::nonNull).map(stack -> {
                NBTTagCompound compound = new NBTTagCompound();
                stack.writeToNBT(compound);
                return compound;
            }).collect(Collectors.toList());
            stacks.put(Integer.valueOf(y * 9 + x), nbts);
        }
        NBTTagCompound tag = new NBTTagCompound();
        for (int i = 0; i < 81; i++) {
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
