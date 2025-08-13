package foxiwhitee.HellIntegrations.integration.botania.container;


import appeng.api.storage.ITerminalHost;
import appeng.container.slot.SlotFakeCraftingMatrix;
import foxiwhitee.HellIntegrations.container.slots.SlotFakeOutput;
import foxiwhitee.HellIntegrations.container.terminals.ContainerPatternTerminal;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;

public class ContainerPartPureDaisyPatternTerminal extends ContainerPatternTerminal {

    private final SlotFakeCraftingMatrix[] craftingSlots = new SlotFakeCraftingMatrix[1];
    private final SlotFakeOutput outputSlot;

    public ContainerPartPureDaisyPatternTerminal(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);

        this.addSlotToContainer(this.craftingSlots[0] = new SlotFakeCraftingMatrix(this.crafting, 0, 403, 87));

        this.addSlotToContainer(this.outputSlot = new SlotFakeOutput(this.output, 403, 168));
    }

    @Override
    protected Item getPattern() {
        return BotaniaIntegration.PURE_DAISY_PATTERN;
    }

    @Override
    protected SlotFakeCraftingMatrix[] getInventoryCraftingSlots() {
        return this.craftingSlots;
    }
}
