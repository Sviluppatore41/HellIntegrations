package foxiwhitee.HellIntegrations.integration.avaritia.container;

import appeng.container.AEBaseContainer;
import foxiwhitee.HellIntegrations.container.slots.CustomSlotRestrictedInput;
import foxiwhitee.HellIntegrations.integration.avaritia.tile.TileNeutronUnifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerNeutronUnifier extends AEBaseContainer {
    public ContainerNeutronUnifier(EntityPlayer ip, TileNeutronUnifier te) {
        super(ip.inventory, (TileEntity)te, null);
        for (int x = 0; x < 36; x++) {
            int y = x / 9;
            addSlotToContainer((Slot)new CustomSlotRestrictedInput(CustomSlotRestrictedInput.PlacableItemType.NEUTRON, te.getInternalInventory(), x, 8 + 18 * x - 162 * y, 29 + 18 * y,
                    getInventoryPlayer()));
        }
        bindPlayerInventory(ip.inventory, 0, 124);
    }
}
