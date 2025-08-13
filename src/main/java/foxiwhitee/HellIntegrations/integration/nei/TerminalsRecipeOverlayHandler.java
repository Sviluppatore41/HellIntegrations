package foxiwhitee.HellIntegrations.integration.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;
import foxiwhitee.HellIntegrations.network.NetworkManager;
import foxiwhitee.HellIntegrations.network.packets.PacketTerminalRecipes;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalsRecipeOverlayHandler implements IOverlayHandler {
    private final int slots;

    public TerminalsRecipeOverlayHandler(int slots) {
        this.slots = slots;
    }

    public void overlayRecipe(GuiContainer firstGui, IRecipeHandler recipe, int recipeIndex, boolean shift) {
        List<PositionedStack> items = recipe.getIngredientStacks(recipeIndex);
        Map<Integer, List<NBTTagCompound>> stacks = new HashMap<>();
        int count = 0;
        for (PositionedStack pStack : items) {
            if (pStack == null)
                continue;
            List<NBTTagCompound> nbts = Arrays.stream(pStack.items)
                    .filter(Objects::nonNull)
                    .map(stack -> {
                        NBTTagCompound compound = new NBTTagCompound();
                        stack.writeToNBT(compound);
                        return compound;
                    })
                    .collect(Collectors.toList());
            stacks.put(count++, nbts);
        }
        NBTTagCompound tag = new NBTTagCompound();
        for (int i = 0; i < slots; i++) {
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
