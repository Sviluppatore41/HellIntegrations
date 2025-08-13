package foxiwhitee.HellIntegrations.integration.avaritia.container;


import appeng.api.storage.ITerminalHost;
import appeng.container.slot.*;
import appeng.util.Platform;
import appeng.util.item.AEItemStack;
import foxiwhitee.HellIntegrations.container.slots.SlotFakeOutput;
import foxiwhitee.HellIntegrations.container.terminals.ContainerPatternTerminal;
import foxiwhitee.HellIntegrations.integration.avaritia.AvaritiaIntegration;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Iterator;

public class ContainerPartBigPatternTerminal extends ContainerPatternTerminal {

    private final SlotFakeCraftingMatrix[] craftingSlots = new SlotFakeCraftingMatrix[81];
    private final SlotFakeOutput outputSlot;

    public ContainerPartBigPatternTerminal(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);
        this.craftingMode = true;
        int y;
        for(y = 0; y < 9; ++y) {
            for(int j = 0; j < 9; ++j) {
                this.addSlotToContainer(this.craftingSlots[j + y * 9] = new SlotFakeCraftingMatrix(this.crafting, j + y * 9, 352 + j * 18, 26 + y * 18));
            }
        }

        this.addSlotToContainer(this.outputSlot = new SlotFakeOutput(this.output, 424, 203));
        this.outputSlot.setIIcon(-1);

    }

    public boolean isSlotEnabled(int idx) {
        if (idx == 1) {
            return Platform.isServer() ? !this.getTerminal().isCraftingRecipe() : !this.isCraftingMode();
        } else if (idx == 2) {
            return Platform.isServer() ? this.getTerminal().isCraftingRecipe() : this.isCraftingMode();
        } else {
            return false;
        }
    }

    @Override
    public void encode() {
        ItemStack output = this.getPatternSlotOUT().getStack();
        AEItemStack[] in = this.getInputs();
        ItemStack[] out = this.getOutputs();
        if (in != null && out != null) {
            if (output == null || this.isPattern(output)) {
                if (output == null) {
                    output = this.getPatternSlotIN().getStack();
                    if (output == null || !this.isPattern(output)) {
                        return;
                    }

                    --output.stackSize;
                    if (output.stackSize == 0) {
                        this.getPatternSlotOUT().putStack((ItemStack)null);
                    }

                    output = new ItemStack(getPattern(), 1);
                    this.getPatternSlotOUT().putStack(output);
                }

                NBTTagCompound encodedValue = new NBTTagCompound();
                NBTTagList tagIn = new NBTTagList();
                NBTTagList tagOut = new NBTTagList();

                for(AEItemStack i : in) {
                    tagIn.appendTag(this.createItemTag(i));
                }

                for(ItemStack i : out) {
                    tagOut.appendTag(this.createItemTag(i));
                }

                encodedValue.setTag("in", tagIn);
                encodedValue.setTag("out", tagOut);
                encodedValue.setBoolean("crafting", this.isCraftingMode());
                encodedValue.setBoolean("substitute", this.isSubstitute());
                output.setTagCompound(encodedValue);
            }
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (Platform.isServer()) {
            if (isCraftingMode() != getTerminal().isCraftingRecipe()) {
                setCraftingMode(getTerminal().isCraftingRecipe());
            }
            this.substitute = this.getTerminal().isSubstitution();
        }
    }

    @Override
    public void onSlotChange(Slot s) {
        if (s == this.getPatternSlotOUT() && Platform.isServer()) {
            ICrafting icrafting;
            label32:
            for(Iterator var2 = this.crafters.iterator(); var2.hasNext(); ((EntityPlayerMP)icrafting).isChangingQuantityOnly = false) {
                Object crafter = var2.next();
                icrafting = (ICrafting)crafter;
                Iterator var5 = this.inventorySlots.iterator();

                while(true) {
                    Object g;
                    do {
                        if (!var5.hasNext()) {
                            continue label32;
                        }

                        g = var5.next();
                    } while(!(g instanceof OptionalSlotFake) && !(g instanceof SlotFakeCraftingMatrix));

                    Slot sri = (Slot)g;
                    icrafting.sendSlotContents(this, sri.slotNumber, sri.getStack());
                }
            }

            this.detectAndSendChanges();
        }

    }

    @Override
    protected Item getPattern() {
        return AvaritiaIntegration.BIG_PATTERN;
    }

    @Override
    protected SlotFakeCraftingMatrix[] getInventoryCraftingSlots() {
        return this.craftingSlots;
    }

    public void toggleSubstitute() {
        this.substitute = !this.substitute;
        detectAndSendChanges();
    }

    public boolean isCraftingMode() {
        return this.craftingMode;
    }

    private void setCraftingMode(boolean craftingMode) {
        this.craftingMode = craftingMode;
    }

    private boolean isSubstitute() {
        return this.substitute;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

}
