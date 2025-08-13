package foxiwhitee.HellIntegrations.items.fluid;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.item.Item;

public final class ItemEmptyFluidCell extends Item {
    public ItemEmptyFluidCell(String name) {
        setUnlocalizedName(name);
        setTextureName(HellCore.MODID.toLowerCase() + ":storageCells/" + name);
    }
}

