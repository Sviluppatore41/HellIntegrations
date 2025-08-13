package foxiwhitee.HellIntegrations.integration.draconic.container.slots;

import foxiwhitee.HellIntegrations.container.slots.HellSlot;
import foxiwhitee.HellIntegrations.integration.draconic.DraconicEvolutionIntegration;
import foxiwhitee.HellIntegrations.integration.draconic.items.ItemDraconicEnergyUpgrades;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class DraconicAssemblerUpgradeSlot extends HellSlot {
    public DraconicAssemblerUpgradeSlot(IInventory inv, int idx, int x, int y) {
        super(inv, idx, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return (itemstack == null ||
                (itemstack.getItem() == DraconicEvolutionIntegration.DRACONIC_ASSEMBLER_UPGRADES && itemstack.getItemDamage() >= 0 && itemstack.getItemDamage() <= 1) ||
                itemstack.getItem() instanceof ItemDraconicEnergyUpgrades);
    }
}
