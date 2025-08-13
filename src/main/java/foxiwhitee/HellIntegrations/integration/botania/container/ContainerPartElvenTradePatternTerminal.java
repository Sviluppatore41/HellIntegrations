package foxiwhitee.HellIntegrations.integration.botania.container;


import appeng.api.storage.ITerminalHost;
import appeng.container.slot.SlotFakeCraftingMatrix;
import foxiwhitee.HellIntegrations.container.slots.SlotFakeOutput;
import foxiwhitee.HellIntegrations.integration.botania.BotaniaIntegration;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;

public class ContainerPartElvenTradePatternTerminal extends ContainerManaPatternTerminal {
    private final SlotFakeCraftingMatrix[] craftingSlots = new SlotFakeCraftingMatrix[3];
    private final SlotFakeOutput outputSlot;

    public ContainerPartElvenTradePatternTerminal(InventoryPlayer ip, ITerminalHost host) {
        super(ip, host);

        this.addSlotToContainer(this.craftingSlots[0] = new SlotFakeCraftingMatrix(this.crafting, 0, 367, 105));
        this.addSlotToContainer(this.craftingSlots[1] = new SlotFakeCraftingMatrix(this.crafting, 1, 403, 105));
        this.addSlotToContainer(this.craftingSlots[2] = new SlotFakeCraftingMatrix(this.crafting, 2, 439, 105));

        this.addSlotToContainer(this.outputSlot = new SlotFakeOutput(this.output, 403, 168));
    }

    @Override
    protected long getManaCost() {
        return 10000;
    }

    @Override
    protected Item getPattern() {
        return BotaniaIntegration.ELVEN_TRADE_PATTERN;
    }

    @Override
    protected SlotFakeCraftingMatrix[] getInventoryCraftingSlots() {
        return this.craftingSlots;
    }
}
