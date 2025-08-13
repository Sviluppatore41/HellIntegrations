package foxiwhitee.HellIntegrations.integration.botania.items.ae;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.item.Item;

public final class ItemEmptyManaCell extends Item {
    public ItemEmptyManaCell(String name) {
        setUnlocalizedName(name);
        setTextureName(HellCore.MODID.toLowerCase() + ":storageCells/" + name);
    }
}

