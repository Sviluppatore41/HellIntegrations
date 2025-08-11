package foxiwhitee.hellmod.container.slots;

import appeng.container.slot.SlotFake;
import appeng.container.slot.SlotFakeCraftingMatrix;
import appeng.helpers.InventoryAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotFakeOutput extends SlotFakeCraftingMatrix {
    public SlotFakeOutput(IInventory inv, int idx, int x, int y) {
        super(inv, idx, x, y);
    }

    public SlotFakeOutput(IInventory inv, int x, int y) {
        super(inv, 0, x, y);
    }

    public void doClick(InventoryAction action, EntityPlayer who) {}
}
