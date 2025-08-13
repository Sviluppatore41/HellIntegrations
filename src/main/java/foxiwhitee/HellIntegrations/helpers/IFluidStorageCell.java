package foxiwhitee.HellIntegrations.helpers;

import net.minecraft.item.ItemStack;

public interface IFluidStorageCell {
    boolean isFluidCell(ItemStack paramItemStack);

    double getIdleDrain(ItemStack paramItemStack);

    long getBytes(ItemStack paramItemStack);

    int getTypes(ItemStack paramItemStack);
}
