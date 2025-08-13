package foxiwhitee.HellIntegrations.integration.thaumcraft.container;

import appeng.api.storage.ITerminalHost;
import appeng.container.slot.SlotFakeCraftingMatrix;
import foxiwhitee.HellIntegrations.container.slots.SlotFakeOutput;
import foxiwhitee.HellIntegrations.container.terminals.ContainerPatternTerminal;
import foxiwhitee.HellIntegrations.integration.thaumcraft.ThaumcraftIntegration;
import foxiwhitee.HellIntegrations.integration.thaumcraft.helpers.ThaumcraftRecipeHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.CrucibleRecipe;

import java.util.List;

public class ContainerPartAlchemicalConstructionPatternTerminal extends ContainerPatternTerminal {
    private final SlotFakeCraftingMatrix[] craftingSlots = new SlotFakeCraftingMatrix[1];
    private final SlotFakeOutput outputSlot;
    public int craftingIndex = 0;


    public ContainerPartAlchemicalConstructionPatternTerminal(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);
        this.addSlotToContainer(this.craftingSlots[0] = new SlotFakeCraftingMatrix(this.crafting, 0, 403, 87));

        this.addSlotToContainer(this.outputSlot = new SlotFakeOutput(this.output, 403, 168));
    }

    public SlotFakeOutput getOutputSlot() {
        return outputSlot;
    }

    @Override
    protected ItemStack[] getOutputs() {
        List crucibleRecipeList = this.getRecipes(this.getInventoryCrafting().getStackInSlot(0));
        return crucibleRecipeList.isEmpty() ? new ItemStack[0] : new ItemStack[]{((CrucibleRecipe)crucibleRecipeList.get(this.craftingIndex)).getRecipeOutput()};
    }

    public List getRecipes(ItemStack centralStack) {
        return ThaumcraftRecipeHelper.findMatchingCrucibleRecipe(centralStack);
    }

    public void receiveEventId(byte id) {
        this.craftingIndex = id;
        List crucibleRecipeList = this.getRecipes(this.getInventoryCrafting().getStackInSlot(0));
        if (!crucibleRecipeList.isEmpty()) {
            ItemStack out = ((CrucibleRecipe)crucibleRecipeList.get(this.craftingIndex)).getRecipeOutput();
            this.getOutputSlot().putStack(out);
        }
    }

    @Override
    protected Item getPattern() {
        return ThaumcraftIntegration.ALCHEMICAL_CONSTRUCTION_PATTERN;
    }

    @Override
    protected SlotFakeCraftingMatrix[] getInventoryCraftingSlots() {
        return this.craftingSlots;
    }

}
