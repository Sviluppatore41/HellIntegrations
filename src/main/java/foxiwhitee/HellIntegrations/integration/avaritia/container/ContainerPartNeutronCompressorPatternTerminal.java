package foxiwhitee.HellIntegrations.integration.avaritia.container;

import appeng.api.storage.ITerminalHost;
import appeng.container.slot.SlotFakeCraftingMatrix;
import appeng.util.item.AEItemStack;
import fox.spiteful.avaritia.crafting.CompressorManager;
import fox.spiteful.avaritia.crafting.CompressorRecipe;
import foxiwhitee.HellIntegrations.container.slots.SlotFakeOutput;
import foxiwhitee.HellIntegrations.container.terminals.ContainerPatternTerminal;
import foxiwhitee.HellIntegrations.integration.avaritia.AvaritiaIntegration;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ContainerPartNeutronCompressorPatternTerminal extends ContainerPatternTerminal {

    private final SlotFakeCraftingMatrix[] craftingSlots = new SlotFakeCraftingMatrix[1];
    private final SlotFakeOutput outputSlot;

    public ContainerPartNeutronCompressorPatternTerminal(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);

        this.addSlotToContainer(this.craftingSlots[0] = new SlotFakeCraftingMatrix(this.crafting, 0, 403, 87));

        this.addSlotToContainer(this.outputSlot = new SlotFakeOutput(this.output, 403, 168));
    }

    @Override
    public void encode() {
        ItemStack output = this.getPatternSlotOUT().getStack();
        AEItemStack[] in = this.getInputs();
        ItemStack[] out = this.getOutputs();
        if (in != null && out != null) {
            in[0] = (AEItemStack) in[0].copy();
            if (output == null || this.isPattern(output)) {
                if (output == null) {
                    output = this.getPatternSlotIN().getStack();
                    if (output == null || !this.isPattern(output)) {
                        return;
                    }

                    --output.stackSize;
                    if (output.stackSize == 0) {
                        this.getPatternSlotIN().putStack((ItemStack)null);
                    }

                    output = new ItemStack(this.getPattern(), 1);
                    this.getPatternSlotOUT().putStack(output);
                }

                NBTTagCompound encodedValue = new NBTTagCompound();
                NBTTagList tagIn = new NBTTagList();
                NBTTagList tagOut = new NBTTagList();
                long l = 1;
                for (int i = 0; i < CompressorManager.getRecipes().size(); ++i) {
                    CompressorRecipe r = CompressorManager.getRecipes().get(i);
                    if (r != null) {
                        if (matches(in[0].getItemStack(), r.getIngredient())) {
                            l = r.getCost();
                            break;
                        }
                    }
                }
                in[0].setStackSize(l);
                tagIn.appendTag(this.createItemTag(in[0]));


                for(ItemStack i : out) {
                    tagOut.appendTag(this.createItemTag(i));
                }

                encodedValue.setTag("in", tagIn);
                encodedValue.setTag("out", tagOut);
                output.setTagCompound(encodedValue);
            }
        }
    }


    private boolean matches(ItemStack in, Object o) {
        ItemStack stack;
        if (o instanceof ItemStack) {
            stack = (ItemStack) o;
            return simpleAreStacksEqual(stack, in);
        } else if (o instanceof ArrayList) {
            ArrayList<?> al = (ArrayList<?>) o;
            if (!al.isEmpty()) {
                stack = (ItemStack) al.get(0);
                return simpleAreStacksEqual(stack, in);
            } else {
                return false;
            }
        } else if (o instanceof String) {
            List<ItemStack> al = OreDictionary.getOres((String)o);
            if (!al.isEmpty()) {
                stack = (ItemStack) al.get(0);
                return simpleAreStacksEqual(stack, in);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2) {
        return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
    }

    @Override
    protected Item getPattern() {
        return AvaritiaIntegration.NEUTRON_PATTERN;
    }

    @Override
    protected SlotFakeCraftingMatrix[] getInventoryCraftingSlots() {
        return this.craftingSlots;
    }
}
