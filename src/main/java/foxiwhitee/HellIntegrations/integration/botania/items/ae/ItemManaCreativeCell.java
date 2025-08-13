package foxiwhitee.HellIntegrations.integration.botania.items.ae;

import foxiwhitee.HellIntegrations.HellCore;
import net.minecraft.item.Item;

public final class ItemManaCreativeCell extends Item {
    public ItemManaCreativeCell(String name) {
        setUnlocalizedName(name);
        setTextureName(HellCore.MODID.toLowerCase() + ":storageCells/" + name);
        this.maxStackSize = 1;
    }
}
