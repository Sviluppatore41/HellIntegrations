package foxiwhitee.HellIntegrations.integration.botania.container;


import appeng.api.storage.ITerminalHost;
import appeng.container.slot.SlotFakeCraftingMatrix;
import appeng.helpers.InventoryAction;
import appeng.util.item.AEItemStack;
import foxiwhitee.HellIntegrations.container.slots.SlotFakeOutput;
import foxiwhitee.HellIntegrations.container.terminals.ContainerPatternTerminal;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerPartPetalsPatternTerminal extends ContainerPatternTerminal {

    private final SlotFakeCraftingMatrix[] craftingSlots = new SlotFakeCraftingMatrix[16];
    private final SlotFakeOutput outputSlot;

    public ContainerPartPetalsPatternTerminal(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);
        for (int i = 0; i < 16; ++i) {
            SlotFakeCraftingMatrix slot;
            if (i == 0) {
                slot = new SlotFakeCraftingMatrix(this.crafting, i, 403, 53);
            } else {
                slot = new SlotFakeCraftingMatrix(this.crafting, i, -9000, -9000);
            }
            this.addSlotToContainer(slot);
            craftingSlots[i] = slot;
        }

        this.addSlotToContainer(this.outputSlot = new SlotFakeOutput(this.output, 403, 168));
    }

    public void doAction(EntityPlayerMP player, InventoryAction action, int slot, long id) {
        if (slot >= 0 && slot < this.inventorySlots.size()) {
            Slot s = this.getSlot(slot);
            if (s instanceof SlotFakeCraftingMatrix && !(s instanceof SlotFakeOutput)) {
                ItemStack hand = player != null ? player.inventory.getItemStack() : null;
                switch (action) {
                    case PICKUP_OR_SET_DOWN:
                        if (hand == null) {
                            s.putStack(this.geLastItemAndSetItToNull());
                        } else {
                            this.addLastStack(s.getStack());
                            ItemStack copy = hand.copy();
                            copy.stackSize = 1;
                            s.putStack(copy);
                        }
                        break;
                    case SHIFT_CLICK:
                        if (hand != null) {
                            ItemStack copy = hand.copy();
                            copy.stackSize = 1;
                            this.addLastStack(s.getStack());
                            s.putStack(copy);
                        }
                        break;
                }
            } else {
                super.doAction(player, action, slot, id);
            }
        } else {
            super.doAction(player, action, slot, id);
        }
    }

    private ItemStack geLastItemAndSetItToNull() {
        for (int i = craftingSlots.length - 1; i >= 0; --i) {
            if (craftingSlots[i].getHasStack()) {
                ItemStack stack = craftingSlots[i].getStack();
                if (i == 0) {
                    return null;
                }
                craftingSlots[i].putStack(null);
                return stack;
            }
        }
        craftingSlots[0].putStack(null);
        return null;
    }

    private void addLastStack(ItemStack stack) {
        if (stack != null) {
            for (int i = 1; i < craftingSlots.length; ++i) {
                if (!craftingSlots[i].getHasStack()) {
                    craftingSlots[i].putStack(stack);
                    return;
                }
            }
        }
    }

    @Override
    public AEItemStack[] getInputs() {
        AEItemStack[] input = new AEItemStack[this.getInventoryCraftingSlots().length + 1];
        boolean hasValue = false;
        AEItemStack add = AEItemStack.create(new ItemStack(Items.wheat_seeds));
        input[0] = add;
        for(int x = 1; x <= this.getInventoryCraftingSlots().length; ++x) {
            input[x] = AEItemStack.create(this.getInventoryCraftingSlots()[x - 1].getStack());
            if (input[x] != null) {
                hasValue = true;
            }
        }

        if (hasValue) {
            return input;
        } else {
            return null;
        }
    }

    @Override
    protected Item getPattern() {
        return BotaniaIntegration.PETALS_PATTERN;
    }

    @Override
    protected SlotFakeCraftingMatrix[] getInventoryCraftingSlots() {
        return this.craftingSlots;
    }

}
